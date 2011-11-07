/*
 * Created on 2010/10/17
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
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
