/**
 * 
 */
package org.mklab.graph.function;

import java.util.NoSuchElementException;


/**
 * Point2Dのイテレータです。
 * <p>
 * 通常のイテレータの機能に加え、逆方向への移動も可能となっています。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 * @param <E> 要素の型です。
 */
public interface TraversableIterator<E> {

  /**
   * 次の点があるかどうか調べます。
   * 
   * @return 次の点があるかどうか
   */
  boolean hasNext();

  /**
   * 前の点があるかどうか調べます。
   * 
   * @return 前の点があるかどうか
   */
  boolean hasPrevious();

  /**
   * 次の点を取得します。
   * 
   * @return 次の点
   * @throws NoSuchElementException 次の点がない場合
   */
  E next();

  /**
   * 前の点を取得します。
   * 
   * @return 前の点
   * @throws NoSuchElementException 前の点がない場合
   */
  E previous();

}
