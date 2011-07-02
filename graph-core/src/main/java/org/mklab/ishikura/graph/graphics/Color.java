/*
 * Created on 2010/10/18
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.graphics;

import java.text.MessageFormat;


/**
 * RGBによる色を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/18
 */
public class Color {

  /** 赤を表す定数です。 */
  public static Color RED = new Color(0xFF, 0, 0);
  /** 緑を表す定数です。 */
  public static Color GREEN = new Color(0, 0xFF, 0);
  /** 青を表す定数です。 */
  public static Color BLUE = new Color(0, 0, 0xFF);
  /** 黒を表す定数です。 */
  public static Color BLACK = new Color(0, 0, 0);
  /** 灰色を表す定数です。 */
  public static Color GRAY = new Color(0x7F, 0x7F, 0x7F);
  /** 白を表す定数です。 */
  public static Color WHITE = new Color(0xFF, 0xFF, 0xFF);

  private int red;
  private int green;
  private int blue;

  /**
   * 新しく生成された<code>Color</code>オブジェクトを初期化します。
   * 
   * @param red 赤
   * @param green 緑
   * @param blue 青
   */
  public Color(int red, int green, int blue) {
    this.red = compressColorComponent(red);
    this.green = compressColorComponent(green);
    this.blue = compressColorComponent(blue);
  }

  /**
   * 色要素の値を適切な値(0~255)に修正します。
   * 
   * @param value 値
   * @return 修正した値
   */
  private int compressColorComponent(int value) {
    if (value < 0) return 0;
    if (value > 255) return 255;
    return value;
  }

  /**
   * 赤成分を取得します。
   * 
   * @return 赤成分(0~255)
   */
  public int getRed() {
    return this.red;
  }

  /**
   * 緑成分を取得します。
   * 
   * @return 緑成分(0~255)
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * 青成分を取得します。
   * 
   * @return 青成分(0~255)
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return MessageFormat.format("Color [red={0}, green={1}, blue={2}]", this.red, this.green, this.blue); //$NON-NLS-1$
  }

}
