/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mklab.abgr.Graphics;
import org.mklab.abgr.NullGraphics;
import org.mklab.ishikura.graph.figure.ContainerFigureImpl;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class GridFigureTest {

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.GridFigure#layout(org.mklab.abgr.Graphics)}
   * .
   */
  @Test
  public void testLayout() {
    final GridFigure figure = new GridFigure();
    final Graphics g = new NullGraphics();

    boolean thrown = false;
    try {
      figure.layout(g);
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertTrue(thrown);

    figure.setScope(new Scope(0, 300, 0, 300));
    figure.layout(g);
    assertNotNull(figure.getGridX());
    assertNotNull(figure.getGridY());

    assertEquals(figure.getWidth(), figure.measureX.getViewSize());
    assertEquals(figure.getHeight(), figure.measureY.getViewSize());
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.GridFigure#modelToViewX(double)} .
   */
  @Test
  public void testModelToView() {
    final GridFigure figure = new GridFigure();
    final Graphics g = new NullGraphics();
    figure.setWidth(300);
    figure.setHeight(300);
    figure.setScope(new Scope(0, 300, 0, 300));
    figure.layout(g);

    assertEquals(299, figure.modelToViewX(299.999));
    assertEquals(-1, figure.modelToViewX(300));
    assertEquals(0, figure.modelToViewX(0));
    assertEquals(0, figure.modelToViewY(299.999));
    assertEquals(299, figure.modelToViewY(0));
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.GridFigure#modelToViewX(double)} .
   */
  @Test
  public void testModelToViewFigure() {
    final GridFigure gridFigure = new GridFigure();
    final Graphics g = new NullGraphics();
    final ContainerFigureImpl parent = new ContainerFigureImpl();

    gridFigure.setX(10);
    gridFigure.setY(10);
    gridFigure.setWidth(300);
    gridFigure.setHeight(300);

    gridFigure.setScope(new Scope(0, 300, 0, 300));
    parent.add(gridFigure);
    parent.draw(g);

    assertEquals(0, gridFigure.modelToViewY(parent, 299.999));
    assertEquals(-1, gridFigure.modelToViewY(parent, 300));
    assertEquals(299, gridFigure.modelToViewY(parent, 0));
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.GridFigure#viewToModelX(int)} .
   */
  @Test
  public void testViewToModel() {
    final GridFigure figure = new GridFigure();
    final Graphics g = new NullGraphics();
    figure.setWidth(300);
    figure.setHeight(300);
    figure.setScope(new Scope(0, 300, 0, 300));
    figure.layout(g);

    assertEquals(300, figure.viewToModelX(299), 0);
    assertEquals(0, figure.viewToModelX(0), 0);
    assertEquals(0, figure.viewToModelY(299), 0);
    assertEquals(300, figure.viewToModelY(0), 0);
  }

}
