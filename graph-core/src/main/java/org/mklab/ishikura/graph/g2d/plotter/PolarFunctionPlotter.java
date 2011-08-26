/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import java.util.Iterator;

import org.mklab.abgr.Graphics;
import org.mklab.ishikura.graph.function.PolarFunction2D;
import org.mklab.ishikura.graph.g2d.GridFigure;


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
