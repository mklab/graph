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
package org.mklab.graph.figure;

import org.mklab.abgr.Graphics;


/**
 * 図形を表すインターフェースです。
 * <p>
 * 図形は与えられた描画対象({@link Graphics})に対する描画を行うものすべてを対象とします。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/12
 */
public interface Figure {

  /**
   * 図の内部状態を適切に（妥当に）します。
   * <p>
   * このメソッドは{@link Figure#draw(Graphics)}時に適宜呼び出されるため、フレームワーク外から通常呼び出す必要はありません。
   * 
   * @param g グラフィックスコンテキスト
   */
  void validate(Graphics g);

  /**
   * 図の領域を設定します。
   * 
   * @param x x
   * @param y y
   * @param width 横幅
   * @param height 縦幅
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * 図のサイズを設定します。
   * 
   * @param width 図の横幅
   * @param height 図の縦幅
   */
  void setSize(int width, int height);

  /**
   * 横幅を取得します。
   * 
   * @return 横幅
   */
  int getWidth();

  /**
   * 横幅を設定します。
   * 
   * @param width 横幅
   */
  void setWidth(int width);

  /**
   * 縦幅を取得します。
   * 
   * @return 縦幅
   */
  int getHeight();

  /**
   * 縦幅を設定します。
   * 
   * @param height 縦幅
   */
  void setHeight(int height);

  /**
   * 図の描画矩形の左上座標のx座標を取得します。
   * 
   * @return x座標
   */
  int getX();

  /**
   * x座標を設定します。
   * 
   * @param x 座標
   */
  void setX(int x);

  /**
   * 図の描画矩形の左上座標のy座標を取得します。
   * 
   * @return y座標
   */
  int getY();

  /**
   * y座標を設定します。
   * 
   * @param y 座標
   */
  void setY(int y);

  /**
   * 図を描画します。
   * 
   * @param g 描画先グラフィックスコンテキスト
   */
  void draw(Graphics g);

  /**
   * 与えられた座標がこの図形に含まれるかどうか調べます。
   * 
   * @param x x座標
   * @param y y座標
   * @return 含まれていればtrue,そうでなければfalse
   */
  boolean contains(int x, int y);

  /**
   * この図の属している親の図を取得します。
   * 
   * @return 親の図
   */
  Figure getParent();
}
