/*
 * Created on 2010/10/12
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.graphics;

/**
 * 描画対象を表すインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/12
 */
public interface Graphics {

  /**
   * 透明度を設定します。
   * 
   * @param alpha 透明度(0~1)
   */
  void setAlpha(float alpha);

  /**
   * 透明度を取得します。
   * 
   * @return 透明度
   */
  float getAlpha();

  /**
   * {@link #drawLine(int, int, int, int)}の線種を設定します。
   * 
   * @param type 線種
   */
  void setLineType(LineType type);

  /**
   * 現在の線種を取得します。
   * 
   * @return 線種
   */
  LineType getLineType();

  /**
   * 線幅を設定します。
   * 
   * @param width 線幅
   */
  void setLineWidth(float width);

  /**
   * 線幅を取得します。
   * 
   * @return 線幅
   */
  float getLineWidth();

  /**
   * 線を描画します。
   * 
   * @param x1 始点のx座標
   * @param y1 始点のy座標
   * @param x2 終点のx座標
   * @param y2 終点のy座標
   */
  void drawLine(int x1, int y1, int x2, int y2);

  /**
   * 文字列を描画します。
   * 
   * @param str 文字列
   * @param x x座標
   * @param y y座標
   */
  void drawString(String str, int x, int y);

  /**
   * 描画色を設定します。
   * 
   * @param c 描画色
   */
  void setColor(Color c);

  /**
   * 現在の色を取得します。
   * 
   * @return 現在の色
   */
  Color getColor();

  /**
   * 矩形を塗りつぶします。
   * 
   * @param x 矩形のx座標
   * @param y 矩形のy座標
   * @param width 矩形の横幅
   * @param height 矩形の縦幅
   */
  void fillRect(int x, int y, int width, int height);

  /**
   * 矩形を描画します。
   * 
   * @param x 矩形のx座標
   * @param y 矩形のy座標
   * @param width 矩形の横幅
   * @param height 矩形の縦幅
   */
  void drawRect(int x, int y, int width, int height);

  /**
   * 文字列を描画した場合の横幅(px)を計算します。
   * 
   * @param text 文字列の横幅
   * @return 横幅
   */
  int computeTextWidth(String text);

  /**
   * 文字列の高さを取得します。
   * 
   * @return 文字列の高さ
   */
  int getTextHeight();

  /**
   * 文字のアセントを取得します。
   * 
   * @return 文字のアセント
   */
  int getTextAscent();

  /**
   * 文字のディセントを取得します。
   * 
   * @return 文字のディセント
   */
  int getTextDescent();

  /**
   * 描画矩形を制限します。
   * <p>
   * この呼び出し以降、 {@link #clearClip()}の呼び出しまで、与えられた矩形以外の描画は行われません。
   * 
   * @param x 矩形の左上のx座標
   * @param y 矩形の左上のy座標
   * @param width 矩形の横幅
   * @param height 矩形の縦幅
   */
  void clipRect(int x, int y, int width, int height);

  /**
   * {@link #clipRect(int, int, int, int)}により制限していた描画域を解除します。
   */
  void clearClip();

  /**
   * すべての描画を与えられた座標分平行移動して行うよう座標変換を行います。
   * 
   * @param dx x方向の移動量
   * @param dy y方向の移動量
   */
  void translate(int dx, int dy);

  /**
   * 回転移動します。
   * 
   * @param theta 回転量
   */
  void rotate(double theta);
}
