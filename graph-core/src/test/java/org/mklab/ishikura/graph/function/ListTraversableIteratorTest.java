/**
 * 
 */
package org.mklab.ishikura.graph.function;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 */
public class ListTraversableIteratorTest extends AbstractTraversableIteratorTest {

  /**
   * @see org.mklab.ishikura.graph.function.AbstractTraversableIteratorTest#create(int)
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
