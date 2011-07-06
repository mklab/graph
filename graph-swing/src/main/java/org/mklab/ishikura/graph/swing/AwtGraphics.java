/*
 * Created on 2010/10/13vv
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.swing;

import java.awt.AlphaComposite;
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
  float lineWidth = 1;
  /** 線種です。 */
  LineType lineType = LineType.DEFAULT;
  /** 現在の色です。 */
  org.mklab.ishikura.graph.graphics.Color color;
  /** 透明度です。 */
  float alpha = 1;

  /**
   * {@link AwtGraphics}オブジェクトを構築します。
   * 
   * @param g AWTグラフィックスコンテキスト
   */
  public AwtGraphics(java.awt.Graphics2D g) {
    if (g == null) throw new NullPointerException();
    this.g = g;
    setColor(org.mklab.ishikura.graph.graphics.Color.BLACK);
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
  public LineType getLineType() {
    return this.lineType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLineType(LineType type) {
    this.lineType = type;
    updateStroke();
  }

  private void updateStroke() {
    switch (this.lineType) {
      case DEFAULT:
        this.g.setStroke(new BasicStroke(this.lineWidth));
        return;
      case DOT:
        this.g.setStroke(new BasicStroke(this.lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] {4, 4}, 0));
        return;
      default:
        throw new IllegalArgumentException(String.valueOf(this.lineType));
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
    if (text == null) return 0;

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
  public void drawPolyline(int[] pointArray) {
    final int pointCount = pointArray.length / 2;
    final int[] xPoints = new int[pointCount];
    final int[] yPoints = new int[pointCount];
    for (int i = 0; i < pointCount; i++) {
      xPoints[i] = pointArray[2 * i];
      yPoints[i] = pointArray[2 * i + 1];
    }
    this.g.drawPolyline(xPoints, yPoints, pointCount);
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLineWidth(float width) {
    if (width <= 0) throw new IllegalArgumentException();

    this.lineWidth = width;
    updateStroke();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public float getLineWidth() {
    return this.lineWidth;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setAlpha(float alpha) {
    if (alpha < 0 || alpha > 1) throw new IllegalArgumentException();
    this.alpha = alpha;
    if (alpha == 1) {
      this.g.setComposite(AlphaComposite.SrcOver);
    } else {
      this.g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public float getAlpha() {
    return this.alpha;
  }

}
