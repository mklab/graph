/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * グリッドを生成するファクトリです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public interface GridFactory {

  /**
   * グリッドを生成します。
   * 
   * @param start 区間の始まり
   * @param end 区間の終わり
   * @return グリッド
   */
  Grid create(double start, double end);

}
