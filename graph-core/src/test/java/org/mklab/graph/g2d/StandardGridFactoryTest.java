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
import static org.mklab.graph.g2d.GridAssertion.assertGridEquals;
import static org.mklab.graph.g2d.GridAssertion.grid;

import org.junit.Test;
import org.mklab.graph.g2d.StandardGridFactory;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public class StandardGridFactoryTest {

  static StandardGridFactory gridFactory = new StandardGridFactory();

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.StandardGridFactory#getMultiplierForMakingNumberBetween0_29(double)}
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
   * {@link org.mklab.graph.g2d.StandardGridFactory#mapGridInterval(double)}
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
   * {@link org.mklab.graph.g2d.StandardGridFactory#getGridInterval(double)}
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
   * {@link org.mklab.graph.g2d.StandardGridFactory#create(double, double)}
   * .
   */
  @Test
  public void testCreate() {
    assertGridEquals(grid(-0.4, -0.2, 0, 0.2, 0.4), gridFactory.create(-0.3, 0.3));
    assertGridEquals(grid(-0.2, -0.1, 0, 0.1, 0.2), gridFactory.create(-0.2, 0.2));

    // 精度的に厳しいもの
    assertGridEquals(grid(-0.4, -0.2, 0, 0.2, 0.4), gridFactory.create(-0.3379, 0.3647));
    assertGridEquals(grid(-200000, -100000, 0, 100000, 200000), gridFactory.create(-154077.167222, 156115.562485));
    assertGridEquals(grid(-0.8, -0.6, -0.4, -0.2, 0, 0.2), gridFactory.create(-0.681267, 0.165232));
    assertGridEquals(grid(), gridFactory.create(-2e-16, -1e-16));
    assertGridEquals(grid(), gridFactory.create(1e-16, 2e-16));
  }

}
