/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public class LogScaleMeasureTest {

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.LogScaleMeasure#modelToViewIgnoreBound(double)}
   * .
   */
  @Test
  public void testModelToViewIgnoreBound() {
    final Measure m = new LogScaleMeasure();
    m.setBound(new Bound(1e-2, 1e2));
    m.setViewSize(100);

    assertEquals(0, m.modelToViewIgnoreBound(1e-2));
    assertEquals(25, m.modelToViewIgnoreBound(1e-1));
    assertEquals(50, m.modelToViewIgnoreBound(1e0));
    assertEquals(75, m.modelToViewIgnoreBound(1e1));
    assertEquals(99, m.modelToViewIgnoreBound(1e2 - 1e-10));
    assertEquals(100, m.modelToViewIgnoreBound(1e2));
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.LogScaleMeasure#modelToView(double)}.
   */
  @Test
  public void testModelToView() {
    final Measure m = new LogScaleMeasure();
    m.setBound(new Bound(1e-2, 1e2));
    m.setViewSize(100);

    assertEquals(-1, m.modelToView(1e-3));
    assertEquals(0, m.modelToView(1e-2));
    assertEquals(25, m.modelToView(1e-1));
    assertEquals(50, m.modelToView(1e0));
    assertEquals(75, m.modelToView(1e1));
    assertEquals(99, m.modelToView(1e2 - 1e-10));
    assertEquals(-1, m.modelToView(1e2));
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.LogScaleMeasure#viewToModel(int)}.
   */
  @Test
  public void testViewToModel() {
    final Measure m = new LogScaleMeasure();
    m.setBound(new Bound(1e-2, 1e2));
    m.setViewSize(100);

    assertEquals(1e-2, m.viewToModel(0), 1e-10);
    assertEquals(1e-1, m.viewToModel(25), 1e-10);
    assertEquals(1e-0, m.viewToModel(50), 1e-10);
    assertEquals(1e1, m.viewToModel(75), 1e-10);
    assertEquals(1e2, m.viewToModel(100), 1e-10);
  }
}
