/**
 * 
 */
package org.mklab.graph.function;

/**
 * 2次元の座標を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class Point2D {

  private double x;
  private double y;

  /**
   * {@link Point2D}オブジェクトを構築します。
   * 
   * @param x x座標
   * @param y y座標
   */
  public Point2D(double x, double y) {
    super();
    this.x = x;
    this.y = y;
  }

  /**
   * x座標を取得します。
   * 
   * @return x座標
   */
  public double getX() {
    return this.x;
  }

  /**
   * y座標を取得します。
   * 
   * @return y座標
   */
  public double getY() {
    return this.y;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings({"nls", "boxing"})
  @Override
  public String toString() {
    return String.format("Point2D [x=%s, y=%s]", this.x, this.y);
  }

}
