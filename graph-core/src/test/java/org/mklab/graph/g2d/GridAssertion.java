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

import static org.junit.Assert.assertArrayEquals;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.mklab.graph.g2d.Grid;


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
