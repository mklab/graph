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
