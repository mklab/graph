/**
 * 
 */
package org.mklab.ishikura.graph.function;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * {@link List}による{@link TraversableIterator}の実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 * @param <E> 要素の型
 */
public class ListTraversableIterator<E> implements TraversableIterator<E> {

  /** すべての要素の配列です。 */
  private List<E> elements;
  /** 次の位置のインデックス値です。 */
  private int position;
  /** 配列中の、要素とみなす部分の開始位置です。 */
  private final int offset;
  /** 配列中の、要素とみなす部分の長さです。 */
  private final int length;

  /**
   * {@link ArrayTraversableIterator}オブジェクトを構築します。
   * <p>
   * 有効領域を指定することができますが、その際部分配列を作り直さないためコストはかかりません。
   * 
   * @param elements 要素
   * @param offset 配列中の、要素とみなす部分の開始位置です。
   * @param length 配列中の、要素とみなす部分の長さです。
   */
  ListTraversableIterator(List<E> elements, int offset, int length) {
    if (elements == null) throw new NullPointerException();
    if (offset + length > elements.size()) throw new IndexOutOfBoundsException();

    this.elements = elements;
    this.offset = offset;
    this.length = length;
    this.position = offset;
  }

  /**
   * {@link ArrayTraversableIterator}オブジェクトを構築します。
   * <p>
   * 有効領域を指定することができますが、その際部分配列を作り直さないためコストはかかりません。
   * 
   * @param elements 要素
   * @param offset 配列中の、要素とみなす部分の開始位置です。
   * @param length 配列中の、要素とみなす部分の長さです。
   */
  ListTraversableIterator(List<E> elements, int offset) {
    this(elements, offset, elements.size() - offset);
  }

  /**
   * {@link ArrayTraversableIterator}オブジェクトを構築します。
   * 
   * @param elements 要素
   */
  ListTraversableIterator(List<E> elements) {
    this(elements, 0, elements.size());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return this.position < this.offset + this.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasPrevious() {
    return this.position > this.offset;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    if (hasNext() == false) throw new NoSuchElementException();
    return this.elements.get(this.position++);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E previous() {
    if (hasPrevious() == false) throw new NoSuchElementException();
    return this.elements.get(--this.position);
  }

}
