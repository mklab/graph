/**
 * 
 */
package org.mklab.graph.g2d;

import static org.mklab.graph.g2d.GridAssertion.assertGridEquals;
import static org.mklab.graph.g2d.GridAssertion.grid;

import org.junit.Test;
import org.mklab.graph.g2d.GridFactory;
import org.mklab.graph.g2d.LogScaleGridFactory;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/26
 */
public class LogScaleGridFactoryTest {

  static GridFactory factory = new LogScaleGridFactory();

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.LogScaleGridFactory#create(double, double)}
   * .
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateThrowsIllegalArgumentException() {
    factory.create(0, 1);
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.LogScaleGridFactory#create(double, double)}
   * .
   */
  @Test
  public void testCreate() {
    assertGridEquals(grid(1e0, 1e1), factory.create(1e0, 1e1));
    assertGridEquals(grid(1e0, 1e1, 1e2), factory.create(1e0, 1e2));
    assertGridEquals(grid(1e-1, 1e0, 1e1, 1e2), factory.create(1e-1, 1e2));
    assertGridEquals(grid(1e-2, 1e-1, 1e0, 1e1, 1e2), factory.create(1e-2, 1e2));

    assertGridEquals(grid(1e0, 1e1, 1e2), factory.create(1e0, 2e1));
    assertGridEquals(grid(1e0, 1e1, 1e2), factory.create(2e0, 2e1));
    assertGridEquals(grid(1e-1, 1e0, 1e1, 1e2), factory.create(0.5e0, 2e1));
  }
}
