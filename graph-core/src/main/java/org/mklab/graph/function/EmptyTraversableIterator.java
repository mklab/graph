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
