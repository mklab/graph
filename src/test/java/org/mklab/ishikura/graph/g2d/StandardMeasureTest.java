/*
 * Crveated on 2010/10/13
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/13
 */
public class StandardMeasureTest {

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.StandardMeasure#modelToView(double)} .
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
   * {@link org.mklab.ishikura.graph.g2d.StandardMeasure#modelToView(double)} .
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
   * {@link org.mklab.ishikura.graph.g2d.StandardMeasure#viewToModel(int)}
   */
  @Test
  public void testViewToModelThrowsIllegalArgumentException() {
    final Measure m = new StandardMeasure();
    m.setViewSize(300);
    m.setBound(new Bound(0, 300));

    assertViewToModelThrowsIllegalArgumentException(m, -1);
    assertViewToModelThrowsIllegalArgumentException(m, 301);
  }

  private void assertViewToModelThrowsIllegalArgumentException(Measure m, int value) {
    try {
      m.viewToModel(value);
    } catch (IllegalArgumentException ex) {
      return;
    }
    fail();
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.StandardMeasure#containsInView(int)}
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
   * {@link org.mklab.ishikura.graph.g2d.StandardMeasure#viewToModel(int)}
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
