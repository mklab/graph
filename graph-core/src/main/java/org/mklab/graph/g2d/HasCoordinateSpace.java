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
 * 座標空間を持つことを表すインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/03
 */
public interface HasCoordinateSpace {

  /**
   * 表示位置を移動します。
   * 
   * @param dx x方向の移動量
   * @param dy y方向の移動量
   */
  void moveScope(int dx, int dy);

  /**
   * 与えられた座標を中心として拡大を行います。
   * 
   * @param x 中心とするx座標
   * @param y 中心とするy座標
   * @param ratio 拡大率
   */
  void scaleScope(int x, int y, double ratio);

  /**
   * 表示範囲を設定します。
   * <p>
   * 現在のグラフがその表示範囲に対応していない場合は自動的に修正します。
   * 
   * @param scope 表示範囲。不正な場合は修正されるため実際の表示範囲が設定した値と異なることもあります。
   */
  void setScope(Scope scope);

  /**
   * ビューのx座標からモデルの座標へ変換します。
   * 
   * @param x x座標
   * @return モデルのx座標
   */
  double viewToModelX(int x);

  /**
   * ビューのy座標からモデルの座標へ変換します。
   * 
   * @param y y座標
   * @return モデルのy座標
   */
  double viewToModelY(int y);

}