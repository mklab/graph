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
package org.mklab.graph.g2d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mklab.graph.g2d.Bound;
import org.mklab.graph.g2d.Measure;
import org.mklab.graph.g2d.StandardMeasure;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/13
 */
public class StandardMeasureTest {

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.StandardMeasure#modelToView(double)} .
   */
  @Test
  public void testModelToView() {
    final Measure m = new StandardMeasure();
    m.setViewSize(2);
    m.setBound(new Bound(0, 2));
    assertEquals(-1, m.modelToView(-1));
    assertEquals(0, m.modelToView(0));
    assertEquals(1, m.modelToView(1));
    assertEquals(1, m.modelToView(1.9));
    assertEquals(1, m.modelToView(1.999));
    assertEquals(-1, m.modelToView(2));

    m.setViewSize(300);
    m.setBound(new Bound(0, 300));

    assertEquals(-1, m.modelToView(-1000));
    assertEquals(-1, m.modelToView(-1));
    assertEquals(0, m.modelToView(0));
    assertEquals(0, m.modelToView(0.1));
    assertEquals(0, m.modelToView(0.4999));
    assertEquals(1, m.modelToView(0.5));
    assertEquals(1, m.modelToView(1));
    assertEquals(1, m.modelToView(1.49));
    assertEquals(299, m.modelToView(299));
    assertEquals(299, m.modelToView(299.999));
    assertEquals(-1, m.modelToView(300));
    assertEquals(-1, m.modelToView(1000));

    m.setViewSize(600);
    m.setBound(new Bound(-300, 300));
    assertEquals(0, m.modelToView(-300));
    assertEquals(300, m.modelToView(0));
    assertEquals(599, m.modelToView(299));

    m.setViewSize(1200);
    assertEquals(0, m.modelToView(-300));
    assertEquals(600, m.modelToView(0));
    assertEquals(1198, m.modelToView(299));
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.StandardMeasure#modelToView(double)} .
   */
  @Test
  public void testModelToViewIgnoreBound() {
    final Measure m = new StandardMeasure();
    m.setViewSize(300);
    m.setBound(new Bound(0, 300));

    assertEquals(301, m.modelToViewIgnoreBound(301));
    assertEquals(-1, m.modelToViewIgnoreBound(-1));
    assertEquals(1000, m.modelToViewIgnoreBound(1000));
    assertEquals(-1000, m.modelToViewIgnoreBound(-1000));
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.StandardMeasure#containsInView(int)}
   */
  @Test
  public void testContains() {
    final Measure m = new StandardMeasure();
    m.setViewSize(300);
    m.setBound(new Bound(0, 300));

    assertTrue(m.containsInView(0));
    assertTrue(m.containsInView(299));
    assertFalse(m.containsInView(300));
    assertFalse(m.containsInView(-1));
    assertFalse(m.containsInView(301));
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.StandardMeasure#viewToModel(int)}
   */
  @Test
  public void testViewToModel() {
    final Measure m = new StandardMeasure();
    m.setViewSize(300);
    m.setBound(new Bound(0, 300));
    assertEquals(0, m.viewToModel(0), 0);
    assertEquals(300, m.viewToModel(299), 0);

    m.setViewSize(600);
    m.setBound(new Bound(0, 300));
    assertEquals(0, m.viewToModel(0), 0);
    assertEquals(300, m.viewToModel(599), 0);

    m.setViewSize(150);
    m.setBound(new Bound(0, 300));
    assertEquals(0, m.viewToModel(0), 0);
    assertEquals(300, m.viewToModel(149), 0.0001);
  }
}
