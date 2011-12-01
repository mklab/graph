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

import org.mklab.abgr.Color;
import org.mklab.abgr.Graphics;


/**
 * ユーザーに情報を通知するステータスバーを表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/18
 */
public class TextFigure extends AbstractFigure {

  /** 描画する文字列です。 */
  private String text;
  /** 文字の色です。 */
  private Color color = Color.BLACK;
  /** 文字列の位置揃え方法です。 */
  private TextAlignment alignment = TextAlignment.LEFT;
  /** 文字列の描画方向です。 */
  private TextOrientation orientation = TextOrientation.HORIZONTAL;

  /**
   * テキストの色を取得します。
   * 
   * @return color
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * テキストの色を設定します。
   * 
   * @param color color
   */
  public void setColor(Color color) {
    if (color == null) throw new NullPointerException();
    this.color = color;
  }

  /**
   * テキストを取得します。
   * 
   * @return テキスト
   */
  public String getText() {
    return this.text;
  }

  /**
   * テキストを設定します。
   * 
   * @param text テキスト。単一行文字列でなければいけません。
   */
  public void setText(String text) {
    final String oldText = this.text;
    this.text = text;

    if (oldText == null && text != null || text != null && text.equals(text) == false) invalidate();
  }

  /**
   * alignmentを設定します。
   * 
   * @param alignment alignment
   */
  public void setAlignment(TextAlignment alignment) {
    if (alignment == null) throw new NullPointerException();

    final TextAlignment old = this.alignment;
    this.alignment = alignment;
    if (old != alignment) invalidate();
  }

  /**
   * orientationを設定します。
   * 
   * @param orientation orientation
   */
  public void setOrientation(TextOrientation orientation) {
    if (orientation == null) throw new NullPointerException();

    final TextOrientation old = this.orientation;
    this.orientation = orientation;
    if (old != orientation) invalidate();
  }

  /**
   * alignmentを取得します。
   * 
   * @return alignment
   */
  public TextAlignment getAlignment() {
    return this.alignment;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    if (this.text == null) return;

    int gx = 0;
    final int gy = g.getTextAscent();
    final int textWidth = g.computeTextWidth(this.text);

    final int w = this.orientation == TextOrientation.HORIZONTAL ? getWidth() : getHeight();
    switch (this.alignment) {
      case LEFT:
        gx = 0;
        break;
      case RIGHT:
        gx = w - textWidth;
        break;
      case CENTER:
        gx = (w - textWidth) / 2;
        break;
    }

    final boolean isVerticalMode = this.orientation == TextOrientation.VERTICAL;
    if (isVerticalMode) {
      g.translate(0, getHeight());
      g.rotate(-Math.PI / 2);
    }
    g.setColor(this.color);
    g.drawString(this.text, gx, gy);
    if (isVerticalMode) {
      g.rotate(Math.PI / 2);
      g.translate(0, -getHeight());
    }
  }
}
