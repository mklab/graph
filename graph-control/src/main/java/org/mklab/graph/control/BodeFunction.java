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

import org.mklab.graph.function.ListFunction2D;
import org.mklab.graph.function.realtime.RealtimeFunction;
import org.mklab.graph.g2d.Scope;
import org.mklab.nfc.matrix.DoubleMatrix;


/**
 * @author Yuhi Ishikura
 */
public abstract class BodeFunction extends ListFunction2D implements RealtimeFunction, Bode.Callback {

  private CanvasUpdater canvasUpdater;
  private boolean computing = false;
  private int inputIndex;
  private Bode bode;
  private Scope scope;
  private int width;

  /**
   * {@link BodeFunction}オブジェクトを構築します。
   * 
   * @param bode ボード線図の計算に用いるオブジェクト
   * @param inputIndex 入力信号のインデックス(0~)
   */
  public BodeFunction(Bode bode, int inputIndex) {
    this.bode = bode;
    this.inputIndex = inputIndex;
  }

  /**
   * ボード線図の再計算を行います。
   * 
   * @param scope 表示されているグラフの領域
   * @param width キャンバスの横幅
   */
  public final void compute(Scope scope, int width) {
    this.computing = true;
    this.bode.compute(scope.getX().getStart(), scope.getX().getEnd(), width, new Bode.Callback() {

      @SuppressWarnings({"unqualified-field-access", "synthetic-access"})
      @Override
      public void computed(DoubleMatrix freqs, DoubleMatrix magnitudes, DoubleMatrix phases) {
        computing = false;
        clearPoints();
        BodeFunction.this.computed(freqs, magnitudes, phases);
        canvasUpdater.requestRedraw(true);
      }

      @Override
      public int getInputIndex() {
        return BodeFunction.this.getInputIndex();
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getInputIndex() {
    return this.inputIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isComputing() {
    return this.computing;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setCanvasUpdater(CanvasUpdater canvasUpdater) {
    this.canvasUpdater = canvasUpdater;
  }

}
