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

import java.util.ArrayList;
import java.util.List;

import org.mklab.graph.function.ListTraversableIterator;
import org.mklab.graph.function.TraversableIterator;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 */
public class ListTraversableIteratorTest extends AbstractTraversableIteratorTest {

  /**
   * @see org.mklab.graph.function.AbstractTraversableIteratorTest#create(int)
   */
  @Override
  protected TraversableIterator<Object> create(int size) {
    final List<Object> list = new ArrayList<Object>(size);
    for (int i = 0; i < size; i++) {
      list.add(new Object());
    }
    return new ListTraversableIterator<Object>(list);
  }

}
