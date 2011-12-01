/*
 * Copyright (C) 2011 Koga Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mklab.graph.function;

import org.junit.Before;
import org.junit.Test;
import org.mklab.graph.function.ArrayTraversableIterator;
import org.mklab.graph.function.TraversableIterator;

import static org.junit.Assert.fail;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 */
public class ArrayTraversableIteratorTest extends AbstractTraversableIteratorTest {

  /** {@link #create(int)}で生成するイテレータの配列のオフセットです。 */
  private int offset;

  /**
   * 各テスト前に必ず実行されます。
   */
  @Before
  public void beforeTest() {
    this.offset = 0;
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#ArrayTraversableIterator(Object[], int, int)}
   * .
   */
  @Test
  public void testArrayTraversableIteratorThrowsException() {
    testArrayTraversableIteratorNotThrowException(10, 0, 0);
    testArrayTraversableIteratorNotThrowException(10, 0, 10);
    testArrayTraversableIteratorThrowsException(10, 0, 11);
    testArrayTraversableIteratorThrowsException(10, 0, 100);
    testArrayTraversableIteratorNotThrowException(10, 5, 0);
    testArrayTraversableIteratorNotThrowException(10, 5, 5);
    testArrayTraversableIteratorThrowsException(10, 5, 6);
    testArrayTraversableIteratorThrowsException(10, 5, 100);
  }

  /**
   * 与えられた条件でインスタンスを生成し、例外がスローされることをテストします。
   * 
   * @param arraySize 行列のサイズ
   * @param offs 行列中の有効領域のオフセット
   * @param length 有効領域のサイズ
   */
  @SuppressWarnings("unused")
  private static void testArrayTraversableIteratorThrowsException(int arraySize, int offs, int length) {
    Object[] obj = new Object[arraySize];
    boolean thrown = false;
    try {
      new ArrayTraversableIterator<Object>(obj, offs, length);
    } catch (IndexOutOfBoundsException ex) {
      thrown = true;
    }
    if (thrown == false) fail();
  }

  /**
   * 与えられた条件でインスタンスを生成し、例外がスローされることをテストします。
   * 
   * @param arraySize 行列のサイズ
   * @param offs 行列中の有効領域のオフセット
   * @param length 有効領域のサイズ
   */
  @SuppressWarnings("unused")
  private static void testArrayTraversableIteratorNotThrowException(int arraySize, int offs, int length) {
    Object[] obj = new Object[arraySize];
    boolean thrown = false;
    try {
      new ArrayTraversableIterator<Object>(obj, offs, length);
    } catch (IndexOutOfBoundsException ex) {
      thrown = true;
    }
    if (thrown) fail();
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#hasNext()}
   * .
   */
  @Test
  public void testHasNextWithOffset() {
    this.offset = 5;
    createAndTestHasNext(0);
    this.offset = 10;
    createAndTestHasNext(1);
    this.offset = 100;
    createAndTestHasNext(100);
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#hasPrevious()}
   * .
   */
  @Test
  public void testHasPreviousWithOffset() {
    this.offset = 100;
    createAndTestHasPrevious(0);
    this.offset = 1000;
    createAndTestHasPrevious(1);
    this.offset = 1;
    createAndTestHasPrevious(100);
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#next()}.
   */
  @Test
  public void testNextWithOffset() {
    this.offset = 1;
    createAndTestNext(0);
    this.offset = 10;
    createAndTestNext(100);
  }

  /**
   * Test method for
   * {@link org.mklab.graph.function.ArrayTraversableIterator#previous()}
   * .
   */
  @Test
  public void testPreviousWithOffset() {
    this.offset = 1;
    createAndTestPrevious(0);
    this.offset = 10;
    createAndTestPrevious(100);
  }

  /**
   * 与えられたサイズでテスト対象のイテレータを生成します。
   * 
   * @param size サイズ
   * @return イテレータ
   */
  @Override
  protected TraversableIterator<Object> create(int size) {
    int arraySize = size + this.offset;
    Object[] array = new Object[arraySize];
    for (int i = 0; i < arraySize; i++)
      array[i] = new Object();

    TraversableIterator<Object> itr = new ArrayTraversableIterator<Object>(array, this.offset, size);
    return itr;
  }

}
