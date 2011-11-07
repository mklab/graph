/**
 * 
 */
package org.mklab.graph.function;

/**
 * 配列による{@link Function2D}の実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public class ArrayFunction2D extends DiscreteFunction2D {

  private Point2D[] points;

  /**
   * {@link ArrayFunction2D}オブジェクトを構築します。
   * 
   * @param points 座標の配列
   */
  public ArrayFunction2D(Point2D... points) {
    if (points == null) throw new NullPointerException();
    this.points = points;
  }

  /**
   * {@link ArrayFunction2D}オブジェクトを構築します。
   * 
   * @param x x方向の値
   * @param y y方向の値
   */
  public ArrayFunction2D(double[] x, double[] y) {
    this(createPointArray(x, y));
  }

  /**
   * 座標配列を作成します。
   * 
   * @param x x座標の配列
   * @param y y座標の配列
   */
  private static Point2D[] createPointArray(double[] x, double[] y) {
    if (x == null || y == null) throw new NullPointerException();
    if (x.length != y.length) throw new IllegalArgumentException();

    final int size = x.length;
    final Point2D[] points = new Point2D[size];
    for (int i = 0; i < size; i++) {
      points[i] = new Point2D(x[i], y[i]);
    }
    return points;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TraversableIterator<Point2D> iterator(double startOfX) {
    for (int i = 0; i < this.points.length; i++) {
      final double x = this.points[i].getX();
      if (x == startOfX) {
        return new ArrayTraversableIterator<Point2D>(this.points, i);
      } else if (x > startOfX) {
        if (i == 0) return new ArrayTraversableIterator<Point2D>(this.points, i);
        return new ArrayTraversableIterator<Point2D>(this.points, i - 1);
      }
    }
    return new EmptyTraversableIterator<Point2D>();
  }

}
