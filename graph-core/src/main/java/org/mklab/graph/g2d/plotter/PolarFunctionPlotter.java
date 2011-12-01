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
package org.mklab.graph.g2d.plotter;

import java.util.Iterator;

import org.mklab.abgr.Graphics;
import org.mklab.graph.function.PolarFunction2D;
import org.mklab.graph.g2d.GridFigure;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
class PolarFunctionPlotter implements Plotter {

  private PolarFunction2D function;

  PolarFunctionPlotter(PolarFunction2D function) {
    if (function == null) throw new NullPointerException();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    final PolylineRenderer polyRenderer = new PolylineRenderer(grid);
    final Iterator<Double> thetas = this.function.provideTheta();
    while (thetas.hasNext()) {
      final double theta = thetas.next().doubleValue();
      final double r = this.function.evalR(theta);

      final double x = r * Math.cos(theta);
      final double y = r * Math.sin(theta);
      if (Plotters.isValidNumber(x) == false || Plotters.isValidNumber(y) == false) continue;

      final int viewX = grid.modelToViewXIgnoreBound(x);
      final int viewY = grid.modelToViewYIgnoreBound(y);

      polyRenderer.appendPoint(viewX, viewY);
    }
    polyRenderer.draw(g);
  }

}
