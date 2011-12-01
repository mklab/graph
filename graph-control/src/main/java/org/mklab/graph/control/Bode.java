/*
 * Copyright (C) 2011 Koga Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mklab.graph.control;

import org.mklab.nfc.matrix.DoubleMatrix;
import org.mklab.nfc.matrix.NumericalMatrix;
import org.mklab.tool.control.LinearSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author Yuhi ishikura
 */
public class Bode {

  private org.mklab.tool.control.Bode bode;
  private Map<CallbackKey, List<Callback>> callbacks = new HashMap<Bode.CallbackKey, List<Bode.Callback>>();
  private ExecutorService executor;

  /**
   * {@link Bode}オブジェクトを構築します。
   * 
   * @param linearSystem 線形システム
   */
  public Bode(LinearSystem linearSystem) {
    this.bode = new org.mklab.tool.control.Bode(linearSystem);
    this.executor = Executors.newSingleThreadExecutor();
  }

  /**
   * ボード線図の計算を行います。
   * 
   * @param xStart 開始周波数
   * @param xEnd 終了周波数
   * @param canvasWidth キャンバスの横幅
   * @param callback 結果を受け取るコールバック
   */
  public synchronized void compute(final double xStart, final double xEnd, final int canvasWidth, Callback callback) {
    final CallbackKey key = new CallbackKey(xStart, xEnd, canvasWidth);
    List<Callback> callbacksForKey = this.callbacks.get(key);
    if (callbacksForKey == null) {
      callbacksForKey = new ArrayList<Bode.Callback>();
      callbacksForKey.add(callback);
      this.callbacks.put(key, callbacksForKey);

      this.executor.execute(new Runnable() {

        @SuppressWarnings("synthetic-access")
        @Override
        public void run() {
          computeNow(xStart, xEnd, canvasWidth);
        }
      });
    } else {
      callbacksForKey.add(callback);
      return;
    }
  }

  private void computeNow(double xStart, double xEnd, int canvasWidth) {
    final List<List<NumericalMatrix<?>>> result = this.bode.getGainAndPhase(computeAngularFrequencies(xStart, xEnd, canvasWidth));
    final CallbackKey callbackKey = new CallbackKey(xStart, xEnd, canvasWidth);
    List<Callback> resultTarget = this.callbacks.get(callbackKey); // ConcurrentModification対策
    if (resultTarget == null) return;

    for (Callback callback : new ArrayList<Callback>(resultTarget)) {
      final int index = callback.getInputIndex();
      final List<NumericalMatrix<?>> magnitudesPhases = result.get(index);
      final DoubleMatrix magnitudes = (DoubleMatrix)magnitudesPhases.get(0);
      final DoubleMatrix phases = (DoubleMatrix)magnitudesPhases.get(1);
      final DoubleMatrix freqs = (DoubleMatrix)magnitudesPhases.get(2);
      callback.computed(freqs, magnitudes, phases);
    }
    resultTarget.clear();
    this.callbacks.remove(callbackKey);
  }

  @SuppressWarnings("static-method")
  private NumericalMatrix<?> computeAngularFrequencies(double xStart, double xEnd, int canvasWidth) {
    final double dx = (xEnd - xStart) / canvasWidth;
    final double[] freqs = new double[canvasWidth];
    double x = xStart;
    for (int i = 0; i < freqs.length; i++) {
      freqs[i] = x;
      x += dx;
    }
    return new DoubleMatrix(freqs);
  }

  static class CallbackKey {

    double xStart;
    double xEnd;
    int canvasWidth;

    CallbackKey(double xStart, double xEnd, int canvasWidth) {
      super();
      this.xStart = xStart;
      this.xEnd = xEnd;
      this.canvasWidth = canvasWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.canvasWidth;
      long temp;
      temp = Double.doubleToLongBits(this.xEnd);
      result = prime * result + (int)(temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(this.xStart);
      result = prime * result + (int)(temp ^ (temp >>> 32));
      return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      CallbackKey other = (CallbackKey)obj;
      if (this.canvasWidth != other.canvasWidth) return false;
      if (Double.doubleToLongBits(this.xEnd) != Double.doubleToLongBits(other.xEnd)) return false;
      if (Double.doubleToLongBits(this.xStart) != Double.doubleToLongBits(other.xStart)) return false;
      return true;
    }

  }

  static interface Callback {

    void computed(DoubleMatrix freqs, DoubleMatrix magnitudes, DoubleMatrix phases);

    /**
     * 入力信号インデックスを取得します。
     * <p>
     * この戻り値が {@link #computed(DoubleMatrix, DoubleMatrix, DoubleMatrix)}
     * でコールバックに返される結果に反映されます。
     * 
     * @return 入力信号インデックス
     */
    int getInputIndex();

  }

}
