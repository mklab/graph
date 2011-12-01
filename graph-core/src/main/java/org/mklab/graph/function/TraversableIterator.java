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
