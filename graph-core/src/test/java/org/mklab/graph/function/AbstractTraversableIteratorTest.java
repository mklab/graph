/**
 * 
 */
package org.mklab.graph.function;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.mklab.graph.function.TraversableIterator;


/**
 * {@link TraversableIterator}のテストクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 */
public abstract class AbstractTraversableIteratorTest {

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#hasNext()}
   * .
   */
  @Test
  public void testHasNext() {
    createAndTestHasNext(0);
    createAndTestHasNext(1);
    createAndTestHasNext(100);
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#hasPrevious()}
   * .
   */
  @Test
  public void testHasPrevious() {
    createAndTestHasPrevious(0);
    createAndTestHasPrevious(1);
    createAndTestHasPrevious(100);
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#next()}.
   */
  @Test
  public void testNext() {
    createAndTestNext(0);
    createAndTestNext(100);
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#previous()}
   * .
   */
  @Test
  public void testPrevious() {
    createAndTestPrevious(0);
    createAndTestPrevious(100);
  }

  /**
   * {@link TraversableIterator}のインスタンスを生成します。
   * 
   * @param size サイズ
   * @return インスタンス
   */
  protected abstract TraversableIterator<Object> create(int size);

  /**
   * 与えられた個数の要素を持つイテレータを生成し、hasNext()のテストを行います。
   * 
   * @param size サイズ
   */
  protected void createAndTestHasNext(int size) {
    TraversableIterator<Object> itr = create(size);
    for (int i = 0; i < size; i++) {
      assertTrue(itr.hasNext());
      itr.next();
    }
    assertFalse(itr.hasNext());
  }

  /**
   * 与えられた個数の要素を持つイテレータを生成し、hasPrevious()のテストを行います。
   * 
   * @param size サイズ
   */
  protected void createAndTestHasPrevious(int size) {
    TraversableIterator<Object> itr = create(size);
    for (int i = 0; i < size; i++) {
      itr.next();
    }
    for (int i = 0; i < size; i++) {
      assertTrue(itr.hasPrevious());
      itr.previous();
    }
    assertFalse(itr.hasPrevious());
  }

  /**
   * 与えられた個数の要素を持つイテレータを生成し、hasPrevious()のテストを行います。
   * 
   * @param size サイズ
   */
  protected void createAndTestNext(int size) {
    TraversableIterator<Object> itr = create(size);
    for (int i = 0; i < size; i++) {
      assertNotNull(itr.next());
    }
    boolean thrown = false;
    try {
      itr.next();
    } catch (NoSuchElementException ex) {
      thrown = true;
    }
    if (thrown == false) fail();
  }

  /**
   * 与えられた個数の要素を持つイテレータを生成し、hasPrevious()のテストを行います。
   * 
   * @param size サイズ
   */
  protected void createAndTestPrevious(int size) {
    TraversableIterator<Object> itr = create(size);
    for (int i = 0; i < size; i++)
      itr.next();
    for (int i = 0; i < size; i++)
      itr.previous();

    boolean thrown = false;
    try {
      itr.previous();
    } catch (NoSuchElementException ex) {
      thrown = true;
    }
    if (thrown == false) fail();
  }

}