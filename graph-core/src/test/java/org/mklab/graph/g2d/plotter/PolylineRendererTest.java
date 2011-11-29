/**
 * 
 */
package org.mklab.graph.g2d.plotter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mklab.graph.g2d.GridFigure;
import org.mklab.graph.g2d.plotter.PolylineRenderer;


/**
 * {@link PolylineRenderer}のテストクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/07
 */
public class PolylineRendererTest {

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
   * .
   */
  @Test
  public void testAppendPointSimple() {
    final PolylineRenderer r = create(10);

    r.appendPoint(1, 2);
    assertArrayEquals(new int[] {1, 2}, r.createPointArray());
    r.appendPoint(3, 4);
    assertArrayEquals(new int[] {1, 2, 3, 4}, r.createPointArray());
    r.appendPoint(8, 5);
    assertArrayEquals(new int[] {1, 2, 3, 4, 8, 5}, r.createPointArray());
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
   * .
   */
  @Test
  public void testAppendPointOutOfGrid() {
    final PolylineRenderer r = create(10);

    r.appendPoint(-1, -1);
    assertArrayEquals(new int[] {}, r.createPointArray());
    r.appendPoint(10, 10);
    assertArrayEquals(new int[] {}, r.createPointArray());
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
   * .
   */
  @Test
  public void testAppendPointInToOut() {
    final PolylineRenderer r = create(10);

    r.appendPoint(0, 0);
    assertArrayEquals(new int[] {0, 0}, r.createPointArray());
    r.appendPoint(-1, -1);
    assertArrayEquals(new int[] {0, 0, -1, -1}, r.createPointArray());
    r.appendPoint(-2, -2);
    assertArrayEquals(new int[] {0, 0, -1, -1}, r.createPointArray());
    r.appendPoint(-3, -3);
    assertArrayEquals(new int[] {0, 0, -1, -1}, r.createPointArray());
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
   * .
   */
  @Test
  public void testAppendPointOutToIn() {
    final PolylineRenderer r = create(10);

    r.appendPoint(-1, -1);
    assertArrayEquals(new int[] {}, r.createPointArray());
    r.appendPoint(0, 0);
    assertArrayEquals(new int[] {-1, -1, 0, 0}, r.createPointArray());
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
   * . グラフ内の点が追加され、グラフ外に行き、その後再びグラフ内に入った場合に、複数の折れ線になっているかどうかテストします。
   */
  @Test
  public void testAppendPointInToOutToIn() {
    final PolylineRenderer r = create(10);

    r.appendPoint(1, 1);
    assertArrayEquals(new int[] {1, 1}, r.createPointArray());
    r.appendPoint(-3, -3);
    assertArrayEquals(new int[] {1, 1, -3, -3}, r.createPointArray());
    r.appendPoint(3, 3);
    assertArrayEquals(new int[] {-3, -3, 3, 3}, r.createPointArray());
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.plotter.PolylineRenderer#filterBySaturator(int, int)}
   * .
   */
  @Test
  public void testFilterBySaturator() {
    final int width = 10;
    final PolylineRenderer r = create(width);
    r.setSaturationEnabled(true);
    r.setSaturator(5);

    assertEquals(9, r.filterBySaturator(9, width));
    assertEquals(10, r.filterBySaturator(10, width));
    assertEquals(0, r.filterBySaturator(0, width));

    assertEquals(-5, r.filterBySaturator(-5, width));
    assertEquals(-5, r.filterBySaturator(-5, width));
    assertEquals(-5, r.filterBySaturator(-5, width));

    assertEquals(width + 5, r.filterBySaturator(width + 5, width));
    assertEquals(width + 5, r.filterBySaturator(width + 6, width));
    assertEquals(width + 5, r.filterBySaturator(width + 7, width));

    r.setSaturationEnabled(false);
    assertEquals(width + 7, r.filterBySaturator(width + 7, width));
  }

  private static PolylineRenderer create(final int width) {
    final GridFigure grid = new GridFigure();
    grid.setSize(width, width);
    final PolylineRenderer r = new PolylineRenderer(grid);
    return r;
  }
}
