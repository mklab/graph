package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;
import org.mklab.ishikura.graph.graphics.LineType;


/**
 * 何も行わない {@link Graphics}実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/20
 */
@SuppressWarnings("unused")
public class NullGraphics implements Graphics {

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#setLineType(org.mklab.ishikura.graph.graphics.LineType)
   */
  @Override
  public void setLineType(LineType type) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#getLineType()
   */
  @Override
  public LineType getLineType() {
    return LineType.DEFAULT;
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#drawLine(int, int, int,
   *      int)
   */
  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {
    // do nothing 
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#drawString(java.lang.String,
   *      int, int)
   */
  @Override
  public void drawString(String str, int x, int y) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#setColor(org.mklab.ishikura.graph.graphics.Color)
   */
  @Override
  public void setColor(Color c) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#fillRect(int, int, int,
   *      int)
   */
  @Override
  public void fillRect(int x, int y, int width, int height) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#drawRect(int, int, int,
   *      int)
   */
  @Override
  public void drawRect(int x, int y, int width, int height) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#computeTextWidth(java.lang.String)
   */
  @Override
  public int computeTextWidth(String text) {
    return 0;
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#getTextHeight()
   */
  @Override
  public int getTextHeight() {
    return 0;
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#clipRect(int, int, int,
   *      int)
   */
  @Override
  public void clipRect(int x, int y, int width, int height) {
    // do nothing 
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#clearClip()
   */
  @Override
  public void clearClip() {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#translate(int, int)
   */
  @Override
  public void translate(int dx, int dy) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#rotate(double)
   */
  @Override
  public void rotate(double theta) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#getTextAscent()
   */
  @Override
  public int getTextAscent() {
    return 0;
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#getTextDescent()
   */
  @Override
  public int getTextDescent() {
    return 0;
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#getColor()
   */
  @Override
  public Color getColor() {
    return null;
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#setLineWidth(float)
   */
  @Override
  public void setLineWidth(float width) {
    // do nothing
  }

  /**
   * @see org.mklab.ishikura.graph.graphics.Graphics#getLineWidth()
   */
  @Override
  public float getLineWidth() {
    return 0;
  }

}
