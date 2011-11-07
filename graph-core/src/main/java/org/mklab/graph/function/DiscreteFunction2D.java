/**
 * 
 */
package org.mklab.graph.function;

/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public abstract class DiscreteFunction2D implements Function2D {

  /**
   * 与えられた区間に存在するすべてのサンプルを取得します。
   * <p>
   * 開始位置と同じx座標の点が存在しない場合は負の方向にその値に最も近いものからのイテレータを返します。
   * 
   * @param startOfX 区間の開始位置。
   * @return 区間の開始位置以降の要素のイテレータ
   */
  public abstract TraversableIterator<Point2D> iterator(double startOfX);

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(double x, double y, double delta) {
    final TraversableIterator<Point2D> t = iterator(x);
    if (t.hasNext() == false) return false;
    final Point2D p2 = t.next();

    return Math.abs(p2.getX() - x) <= delta && Math.abs(p2.getY() - y) <= delta;
  }
}
