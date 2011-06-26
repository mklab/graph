/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import static org.junit.Assert.assertEquals;
import static org.mklab.ishikura.graph.g2d.GridAssertion.assertGridEquals;
import static org.mklab.ishikura.graph.g2d.GridAssertion.grid;

import org.junit.Test;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public class StandardGridFactoryTest {

  static StandardGridFactory gridFactory = new StandardGridFactory();

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.StandardGridFactory#getMultiplierForMakingNumberBetween0_29(double)}
   * .
   */
  @Test
  public void testGetMultiplierForMakingNumberBetween0_29() {
    for (double i = 3; i < 30; i += 0.1) {
      assertEquals(1, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }
    for (double i = 30; i < 300; i += 0.1) {
      assertEquals(0.1, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }
    for (double i = 300; i < 3000; i += 0.1) {
      assertEquals(0.01, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }

    for (double i = -3; i > -30; i -= 0.1) {
      assertEquals(-1, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }
    for (double i = -30; i > -300; i -= 0.1) {
      assertEquals(-0.1, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }
    for (double i = -300; i > 3000; i -= 0.1) {
      assertEquals(-0.01, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }

    for (double i = 0.3; i <= 2.9; i += 0.01) {
      assertEquals(10, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }
    for (double i = 0.03; i <= 0.029; i += 0.01) {
      assertEquals(100, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }
    for (double i = 0.003; i <= 0.0029; i += 0.01) {
      assertEquals(1000, gridFactory.getMultiplierForMakingNumberBetween0_29(i), 1e-10);
    }
    assertEquals(0.1, gridFactory.getMultiplierForMakingNumberBetween0_29(290.1), 1e-10);
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.StandardGridFactory#mapGridInterval(double)}
   * .
   */
  @Test
  public void testMapGridInterval() {
    for (double d = 3; d <= 5; d += 0.1) {
      assertEquals(1, gridFactory.mapGridInterval(d), 1e-10);
    }
    for (double d = 6; d <= 11; d += 0.1) {
      assertEquals(2, gridFactory.mapGridInterval(d), 1e-10);
    }
    for (double d = 12; d <= 14; d += 0.1) {
      assertEquals(4, gridFactory.mapGridInterval(d), 1e-10);
    }
    for (double d = 15; d <= 29; d += 0.1) {
      assertEquals(5, gridFactory.mapGridInterval(d), 1e-10);
    }
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.StandardGridFactory#getGridInterval(double)}
   * .
   */
  @Test
  public void testGetGridInterval() {
    for (double d = 3; d <= 5; d += 0.1) {
      assertEquals(1, gridFactory.getGridInterval(d), 1e-10);
    }
    for (double d = 6; d <= 11; d += 0.1) {
      assertEquals(2, gridFactory.getGridInterval(d), 1e-10);
    }
    for (double d = 12; d <= 14; d += 0.1) {
      assertEquals(4, gridFactory.getGridInterval(d), 1e-10);
    }
    for (double d = 15; d <= 29; d += 0.1) {
      assertEquals(5, gridFactory.getGridInterval(d), 1e-10);
    }

    for (double d = 30; d < 60; d += 0.1) {
      assertEquals(10, gridFactory.getGridInterval(d), 1e-10);
    }
    for (double d = 60; d < 120; d += 0.1) {
      assertEquals(20, gridFactory.getGridInterval(d), 1e-10);
    }
    for (double d = 120; d < 150; d += 0.1) {
      assertEquals(40, gridFactory.getGridInterval(d), 1e-10);
    }
    for (double d = 150; d < 300; d += 0.1) {
      assertEquals(50, gridFactory.getGridInterval(d), 1e-10);
    }

    assertEquals(0.2, gridFactory.getGridInterval(0.3674 - (-0.3379)), 1e-10);
  }

  /**
   * Test method for
   * {@link org.mklab.ishikura.graph.g2d.StandardGridFactory#create(double, double)}
   * .
   */
  @Test
  public void testCreate() {
    assertGridEquals(grid(-0.2, 0, 0.2), gridFactory.create(-0.3379, 0.3647));
    assertGridEquals(grid(-100000, 0, 100000), gridFactory.create(-154077.167222, 156115.562485));
    assertGridEquals(grid(-0.6, -0.4, -0.2, 0), gridFactory.create(-0.681267, 0.165232));
  }

}
