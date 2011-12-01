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

import java.text.MessageFormat;


/**
 * 二次元の表示範囲を表すクラスです。
 * <p>
 * このクラスでは、数学上での値の範囲を保持します。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/16
 */
public class Scope {

  /** x方向の範囲です。 */
  private Bound x;
  /** y方向の範囲です。 */
  private Bound y;

  /**
   * {@link Scope}オブジェクトを構築します。
   * 
   * @param x x方向の範囲
   * @param y y方向の範囲
   */
  private Scope(Bound x, Bound y) {
    if (x == null || y == null) throw new NullPointerException();
    this.x = x;
    this.y = y;
  }

  /**
   * {@link Scope}オブジェクトを構築します。
   * 
   * @param startX x方向の開始位置
   * @param endX x方向の終了位置
   * @param startY y方向の開始位置
   * @param endY y方向の終了位置
   */
  public Scope(double startX, double endX, double startY, double endY) {
    this(new Bound(startX, endX), new Bound(startY, endY));
  }

  /**
   * このスコープを移動した新しいスコープを生成します。
   * 
   * @param dx x方向の移動量
   * @param dy y方向の移動量
   * @return 移動後のスコープ
   */
  public Scope translatedScope(double dx, double dy) {
    return new Scope(this.x.getStart() + dx, this.x.getEnd() + dx, this.y.getStart() + dy, this.y.getEnd() + dy);
  }

  /**
   * 与えられた点を中心として拡大した新しいスコープを生成します。
   * 
   * @param baseX 拡大の中心のx座標
   * @param baseY 拡大の中心のy座標
   * @param ratio 拡大率
   * @return 拡大した新しいスコープ
   */
  public Scope scaledScope(double baseX, double baseY, double ratio) {
    return new Scope(this.x.scaledBound(baseX, ratio), this.y.scaledBound(baseY, ratio));
  }

  /**
   * xを取得します。
   * 
   * @return x
   */
  public Bound getX() {
    return this.x;
  }

  /**
   * yを取得します。
   * 
   * @return y
   */
  public Bound getY() {
    return this.y;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return MessageFormat.format("Scope [{0}<=x<={1}, {2}<=y<={3}]", this.x.getStart(), this.x.getEnd(), this.y.getStart(), this.y.getEnd()); //$NON-NLS-1$
  }
}
