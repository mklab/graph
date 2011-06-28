/**
 * 
 */
package org.mklab.ishikura.graph.function;

/**
 * 配列による{@link Function2D}の実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public class ArrayFunction2D extends Function2D implements DiscreteFunction2D {

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
  public double evalY(double x) {
    final TraversableIterator<Point2D> itr = iterator(x);
    return calculateContinuousY(x, itr);
  }

  /**
   * 連続時間のxから、離散時間前後の座標を用いることで補間し、連続時間のyを計算します。
   * 
   * @param x x座標
   * @return y座標
   */
  static double calculateContinuousY(double x, TraversableIterator<Point2D> itr) {

    final Point2D p1 = itr.hasNext() ? itr.next() : null;
    final Point2D p2 = itr.hasNext() ? itr.next() : null;
    /* 関数の範囲内 */
    if (p1 != null && p2 != null) {
      final double a = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
      return a * (x - p1.getX()) + p1.getY();
    }
    /* 関数の範囲外 */
    if (p1 == null && p2 == null) return Double.NaN;

    if (p2 != null) {
      /* 関数の左端 */
      if (p2.getX() == x) return p2.getY(); // TODO デルタを設ける
      return Double.NaN;
    }
    if (p1 != null) {
      /* 関数の右端 */
      if (p1.getX() == x) return p1.getY(); // TODO デルタを設ける
      return Double.NaN;
    }

    // unreachable
    throw new RuntimeException();
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
