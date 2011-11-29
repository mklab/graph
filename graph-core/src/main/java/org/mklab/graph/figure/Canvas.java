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
package org.mklab.graph.figure;

/**
 * 描画対象のキャンバスを表すインターフェースです。
 * 
 * @author Yuhi Ishikura
 */
public interface Canvas {

  /**
   * キャンバス描画領域の横幅を取得します。
   * 
   * @return キャンバス描画領域の横幅
   */
  int getCanvasWidth();

  /**
   * キャンバス描画領域の縦幅を取得します。
   * 
   * @return キャンバス描画領域の縦幅
   */
  int getCanvasHeight();

  /**
   * 再描画を行います。
   */
  void redrawCanvas();

  /**
   * キャンバスリスナーを追加します。
   * 
   * @param canvasListener キャンバスリスナー
   */
  void addCanvasListener(CanvasListener canvasListener);

  /**
   * キャンバスリスナーを削除します。
   * 
   * @param canvasListener キャンバスリスナー
   */
  void removeCanvasListener(CanvasListener canvasListener);

}
