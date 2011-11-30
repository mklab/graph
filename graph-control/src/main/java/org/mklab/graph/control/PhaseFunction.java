/*
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


/**
 * @author Yuhi Ishikura
 */
public class PhaseFunction extends BodeFunction {

  /**
   * {@link PhaseFunction}オブジェクトを構築します。
   * 
   * @param bode ボード線図計算オブジェクト
   * @param inputIndex 入力信号のインデックス(0~)
   */
  public PhaseFunction(Bode bode, int inputIndex) {
    super(bode, inputIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void computed(DoubleMatrix freqs, @SuppressWarnings("unused") DoubleMatrix magnitudes, DoubleMatrix phases) {
    final int cols = freqs.getColumnSize();
    for (int i = 0; i < cols; i++) {
      final int indexInMatrix = i + 1;
      addPoint(freqs.getDoubleElement(indexInMatrix), phases.getDoubleElement(indexInMatrix));
    }
  }
}
