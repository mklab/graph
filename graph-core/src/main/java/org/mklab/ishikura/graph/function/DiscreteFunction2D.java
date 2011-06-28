/**
 * 
 */
package org.mklab.ishikura.graph.function;

/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public interface DiscreteFunction2D {

  /**
   * 与えられた区間に存在するすべてのサンプルを取得します。
   * <p>
   * 開始位置と同じx座標の点が存在しない場合は負の方向にその値に最も近いものからのイテレータを返します。
   * 
   * @param startOfX 区間の開始位置。
   * @return 区間の開始位置以降の要素のイテレータ
   */
  TraversableIterator<Point2D> iterator(double startOfX);

}
