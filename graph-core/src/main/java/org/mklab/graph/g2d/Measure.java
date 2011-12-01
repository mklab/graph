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
package org.mklab.graph.g2d;

/**
 * 数学的な値から、ビュー上の値に変換を行うクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
interface Measure {

  /**
   * ビュー上の表示範囲を設定します。
   * 
   * @param bound 表示範囲
   */
  void setBound(Bound bound);

  /**
   * viewSizeを設定します。
   * 
   * @param viewSize viewSize
   */
  void setViewSize(int viewSize);

  /**
   * viewSizeを取得します。
   * 
   * @return viewSize
   */
  int getViewSize();

  /**
   * ビューとモデルの比率を計算します。
   * 
   * @return ビューとモデルの比率
   */
  double getViewToModelRatio();

  /**
   * 数学的な座標からビュー上の座標に変換します。
   * <p>
   * ビューの範囲外でも計算します。
   * 
   * @param modelValue 数学的な座標
   * @return ビュー上の座標
   */
  int modelToViewIgnoreBound(double modelValue);

  /**
   * 数学的な座標からビュー上の座標に変換します。
   * <p>
   * ビューの範囲外ならば-1を返します。
   * 
   * @param modelValue 数学的な座標
   * @return ビュー上の座標。範囲外ならば-1
   */
  int modelToView(double modelValue);

  /**
   * ビュー上の座標から数学上の座標に変換します。
   * 
   * @param viewValue ビュー上の座標
   * @return 数学的な座標。座標系で存在し得ない場合(ex:対数グラフでviewValue<=0)には、{@link Double#NaN}
   *         を返します。
   */
  double viewToModel(int viewValue);

  /**
   * 与えられた値が含まれているかどうか判定します。
   * 
   * @param viewValue ビュー上の座標
   * @return 含まれるかどうか
   */
  boolean containsInView(int viewValue);

}