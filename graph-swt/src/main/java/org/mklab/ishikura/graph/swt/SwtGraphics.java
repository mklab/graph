package org.mklab.ishikura.graph.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Transform;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;
import org.mklab.ishikura.graph.graphics.LineType;


/**
 * {@link GC}に対し描画を行う{@link Graphics}の実装です。
 * 
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
public class SwtGraphics implements Graphics {

  GC g;
  float alpha;
  LineType lineType;
  float lineWidth;
  Color color;

  /**
   * {@link SwtGraphics}オブジェクトを構築します。
   * 
   * @param g グラフィックスコンテキスト
   */
  public SwtGraphics(GC g) {
    if (g == null) throw new NullPointerException();
    this.g = g;

    setAlpha(1f);
    setLineType(LineType.DEFAULT);
    setColor(Color.BLACK);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setAlpha(float alpha) {
    final int alphaInInt = (int)(alpha * 255);
    this.g.setAlpha(alphaInInt);
    this.alpha = alpha;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public float getAlpha() {
    return this.alpha;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLineType(LineType type) {
    switch (type) {
      case DEFAULT:
        this.g.setLineStyle(SWT.LINE_SOLID);
        break;
      case DOT:
        this.g.setLineStyle(SWT.LINE_DOT);
        break;
    }
    this.lineType = type;
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
  public void setLineWidth(float width) {
    this.g.setLineWidth((int)(width + 0.5f));
    this.lineWidth = width;
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
  public void drawLine(int x1, int y1, int x2, int y2) {
    this.g.drawLine(x1, y1, x2, y2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void drawString(String str, int x, int y) {
    this.g.drawText(str, x, y - getTextAscent(), SWT.DRAW_TRANSPARENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setColor(Color c) {
    final org.eclipse.swt.graphics.Color swtColor = new org.eclipse.swt.graphics.Color(this.g.getDevice(), c.getRed(), c.getGreen(), c.getBlue());
    this.g.setForeground(swtColor);
    this.g.setBackground(swtColor);
    this.color = c;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillRect(int x, int y, int width, int height) {
    this.g.fillRectangle(x, y, width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void drawRect(int x, int y, int width, int height) {
    this.g.drawRectangle(x, y, width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int computeTextWidth(String text) {
    return this.g.stringExtent(text).x;
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
  public void clipRect(int x, int y, int width, int height) {
    this.g.setClipping(x, y, width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearClip() {
    this.g.setClipping((Region)null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void translate(int dx, int dy) {
    final Transform transform = new Transform(this.g.getDevice());
    this.g.getTransform(transform);
    transform.translate(dx, dy);
    this.g.setTransform(transform);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void rotate(double theta) {
    final Transform transform = new Transform(this.g.getDevice());
    transform.rotate((float)theta);
    this.g.setTransform(transform);
  }

}
