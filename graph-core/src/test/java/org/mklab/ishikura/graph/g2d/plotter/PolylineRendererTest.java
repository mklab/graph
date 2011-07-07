/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mklab.ishikura.graph.g2d.GridFigure;


/**
 * {@link PolylineRenderer}のテストクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/07
 */
public class PolylineRendererTest {

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
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
   * {@link org.mklab.ishikura.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
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
   * {@link org.mklab.ishikura.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
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
   * {@link org.mklab.ishikura.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
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
   * {@link org.mklab.ishikura.graph.g2d.plotter.PolylineRenderer#appendPoint(int, int)}
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

  private PolylineRenderer create(final int width) {
    final GridFigure grid = new GridFigure();
    grid.setSize(width, width);
    final PolylineRenderer r = new PolylineRenderer(grid);
    return r;
  }
}
