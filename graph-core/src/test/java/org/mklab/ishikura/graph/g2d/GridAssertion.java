/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import static org.junit.Assert.assertArrayEquals;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/26
 */
class GridAssertion {

  static void assertGridEquals(Grid expected, Grid actual) {
    assertArrayEquals(MessageFormat.format("expected:{0}, actual:{1}", expected, actual), getGridElements(expected), getGridElements(actual)); //$NON-NLS-1$
  }

  static Grid grid(double... d) {
    Grid grid = new Grid();
    for (double dd : d) {
      grid.add(dd);
    }
    return grid;
  }

  private static Double[] getGridElements(Grid g) {
    List<Double> grids = new ArrayList<Double>();
    for (Double d : g) {
      grids.add(d);
    }
    return grids.toArray(new Double[grids.size()]);
  }

}
