/*
 * Created on 2010/10/13vv
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.swing;

import java.awt.BasicStroke;
import java.awt.Color;

import org.mklab.ishikura.graph.graphics.Graphics;
import org.mklab.ishikura.graph.graphics.LineType;


/**
 * {@link java.awt.Graphics}による {@link Graphics}実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/13
 */
public class AwtGraphics implements Graphics {

  /** 描画対象グラフィックスコンテキストです。 */
  java.awt.Graphics2D g;
  /** 線の幅です。 */
  int lineWidth = 1;
  /** 現在の色です。 */
  org.mklab.ishikura.graph.graphics.Color color;

  /**
   * {@link AwtGraphics}オブジェクトを構築します。
   * 
   * @param g AWTグラフィックスコンテキスト
   */
  public AwtGraphics(java.awt.Graphics2D g) {
    if (g == null) throw new NullPointerException();
    this.g = g;
    g.setColor(Color.BLACK);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {
    this.g.drawLine(x1, y1, x2, y2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLineType(LineType type) {
    this.g.setStroke(new BasicStroke(this.lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] {4, 4}, 0));
    switch (type) {
      case DEFAULT:
        this.g.setStroke(new BasicStroke(this.lineWidth));
        return;
      case DOT:
        this.g.setStroke(new BasicStroke(this.lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] {4, 4}, 0));
        return;
      default:
        throw new IllegalArgumentException(String.valueOf(type));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void drawString(String str, int x, int y) {
    this.g.drawString(str, x, y);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int computeTextWidth(String text) {
    return this.g.getFontMetrics().stringWidth(text);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTextHeight() {
    return this.g.getFontMetrics().getHeight();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setColor(org.mklab.ishikura.graph.graphics.Color c) {
    if (c == null) throw new NullPointerException();
    this.g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue()));
    this.color = c;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillRect(int x, int y, int width, int height) {
    this.g.fillRect(x, y, width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void drawRect(int x, int y, int width, int height) {
    this.g.drawRect(x, y, width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clipRect(int x, int y, int width, int height) {
    this.g.clipRect(x, y, width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearClip() {
    this.g.setClip(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void translate(int dx, int dy) {
    this.g.translate(dx, dy);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void rotate(double theta) {
    this.g.rotate(theta);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTextAscent() {
    return this.g.getFontMetrics().getAscent();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTextDescent() {
    return this.g.getFontMetrics().getDescent();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public org.mklab.ishikura.graph.graphics.Color getColor() {
    return this.color;
  }

}
