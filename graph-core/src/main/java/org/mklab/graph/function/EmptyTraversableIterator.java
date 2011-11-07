/**
 * 
 */
package org.mklab.graph.function;

/**
 * 空の {@link TraversableIterator}を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 * @param <E> 要素の型
 */
class EmptyTraversableIterator<E> implements TraversableIterator<E> {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasPrevious() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    throw new IndexOutOfBoundsException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E previous() {
    throw new IndexOutOfBoundsException();
  }

}
