/**
 * 
 */
package org.mklab.ishikura.graph.figure;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * {@link Figures}のテストを行うクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public class FiguresTest {

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.figure.Figures#getAbsoluteLocation(Figure)}
   * .
   */
  @Test
  public void testGetAbsoluteLocation() {
    final ContainerFigureImpl f1 = new ContainerFigureImpl();
    assertEquals(new Point(0, 0), Figures.getAbsoluteLocation(f1));

    final ContainerFigureImpl f2 = new ContainerFigureImpl();
    f2.setX(10);
    f2.setY(10);
    f1.add(f2);
    assertEquals(new Point(10, 10), Figures.getAbsoluteLocation(f2));

    final ContainerFigureImpl f3 = new ContainerFigureImpl();
    f3.setX(10);
    f3.setY(10);
    f2.add(f3);

    assertEquals(new Point(20, 20), Figures.getAbsoluteLocation(f3));
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.figure.Figures#convertPoint(org.mklab.ishikura.graph.figure.Figure, org.mklab.ishikura.graph.figure.Figure, org.mklab.ishikura.graph.figure.Point)}
   * .
   */
  @Test
  public void testConvertPoint() {
    final ContainerFigureImpl f1 = new ContainerFigureImpl();
    assertEquals(new Point(5, 5), Figures.convertPoint(f1, f1, new Point(5, 5)));

    final ContainerFigureImpl f2 = new ContainerFigureImpl();
    f2.setX(10);
    f2.setY(10);
    f1.add(f2);
    assertEquals(new Point(15, 15), Figures.convertPoint(f2, f1, new Point(5, 5)));
    assertEquals(new Point(-5, -5), Figures.convertPoint(f1, f2, new Point(5, 5)));

    final ContainerFigureImpl f3 = new ContainerFigureImpl();
    f3.setX(10);
    f3.setY(10);
    f2.add(f3);
    assertEquals(new Point(25, 25), Figures.convertPoint(f3, f1, new Point(5, 5)));
    assertEquals(new Point(-15, -15), Figures.convertPoint(f1, f3, new Point(5, 5)));
  }

}
