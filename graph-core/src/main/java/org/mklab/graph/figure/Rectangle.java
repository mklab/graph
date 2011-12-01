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

/**
 * 矩形を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/17
 */
public class Rectangle {

  /** x座標です。 */
  public int x;
  /** y座標です。 */
  public int y;
  /** 横幅です。 */
  public int width;
  /** 縦幅です。 */
  public int height;

  /**
   * {@link Rectangle}オブジェクトを構築します。
   * 
   * @param x x座標
   * @param y y座標
   * @param width 横幅
   * @param height 縦幅
   */
  public Rectangle(int x, int y, int width, int height) {
    super();
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

}
