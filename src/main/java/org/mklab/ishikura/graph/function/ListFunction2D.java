/**
 * 
 */
package org.mklab.ishikura.graph.function;

import java.util.ArrayList;
import java.util.List;


/**
 * {@link List}によって座標を与える離散関数を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 */
public class ListFunction2D extends Function2D implements DiscreteFunction2D {

  private List<Point2D> points;
  private double maximumX = -Double.MAX_VALUE;

  /**
   * {@link ListFunction2D}オブジェクトを構築します。
   * 
   * @param p 座標
   */
  public ListFunction2D(Point2D... p) {
    if (p == null) throw new NullPointerException();

    for (Point2D pp : p) {
      addPoint(pp);
    }
  }

  /**
   * {@link ListFunction2D}オブジェクトを構築します。
   */
  public ListFunction2D() {
    this.points = new ArrayList<Point2D>();
  }

  /**
   * 座標を追加します。
   * 
   * @param x x座標
   * @param y y座標
   */
  public void addPoint(double x, double y) {
    addPoint(new Point2D(x, y));
  }

  /**
   * 座標を追加します。
   * 
   * @param point 追加する座標
   */
  public void addPoint(Point2D point) {
    if (point == null) throw new NullPointerException();
    if (point.getX() < this.maximumX) throw new IllegalArgumentException("point addition should be monotone increasement."); //$NON-NLS-1$

    this.points.add(point);
    this.maximumX = point.getX();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double evalY(double x) {
    return ArrayFunction2D.calculateContinuousY(x, iterator(x));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TraversableIterator<Point2D> iterator(double startOfX) {
    for (int i = 0; i < this.points.size(); i++) {
      final double x = this.points.get(i).getX();
      if (x == startOfX) {
        return new ListTraversableIterator<Point2D>(this.points, i);
      } else if (x > startOfX) {
        if (i == 0) return new ListTraversableIterator<Point2D>(this.points, i);
        return new ListTraversableIterator<Point2D>(this.points, i - 1);
      }
    }
    return new EmptyTraversableIterator<Point2D>();
  }

}
