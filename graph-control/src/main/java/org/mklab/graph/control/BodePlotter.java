/*
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
package org.mklab.graph.control;

import org.mklab.abgr.Graphics;
import org.mklab.graph.g2d.Bound;
import org.mklab.graph.g2d.GridFigure;
import org.mklab.graph.g2d.plotter.DiscreteFunctionPlotter;

import java.util.Arrays;


/**
 * @author Yuhi Ishikura
 */
public class BodePlotter extends DiscreteFunctionPlotter {

  private double[] previousFreqs;

  /**
   * {@link BodePlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  public BodePlotter(BodeFunction function) {
    super(function);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized void plot(Graphics g, GridFigure grid) {
    final Bound xBound = grid.getScope().getX();
    final double[] freqs = computeAngularFrequencies(xBound.getStart(), xBound.getEnd(), grid.getWidth());
    if (this.previousFreqs == null || Arrays.equals(this.previousFreqs, freqs) == false) {
      ((BodeFunction)getFunction()).compute(freqs);
    }
    this.previousFreqs = freqs;
    super.plot(g, grid);
  }

  @SuppressWarnings("static-method")
  private double[] computeAngularFrequencies(double xStart, double xEnd, int canvasWidth) {
    final double dx = (xEnd - xStart) / canvasWidth;
    final double[] freqs = new double[canvasWidth];
    double x = xStart;
    for (int i = 0; i < freqs.length; i++) {
      freqs[i] = x;
      x += dx;
    }
    return freqs;
  }

}
