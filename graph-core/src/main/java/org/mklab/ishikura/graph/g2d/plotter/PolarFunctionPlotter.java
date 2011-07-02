/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import java.util.Iterator;

import org.mklab.ishikura.graph.function.PolarFunction2D;
import org.mklab.ishikura.graph.g2d.GridFigure;
import org.mklab.ishikura.graph.graphics.Graphics;


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
    int oldViewX = -1;
    int oldViewY = -1;
    final Iterator<Double> thetas = this.function.provideTheta();
    while (thetas.hasNext()) {
      final double theta = thetas.next().doubleValue();
      final double r = this.function.evalR(theta);

      final double x = r * Math.cos(theta);
      final double y = r * Math.sin(theta);
      if (Double.isNaN(x) || Double.isNaN(y)) continue;

      final int viewX = grid.modelToViewXIgnoreBound(x);
      final int viewY = grid.modelToViewYIgnoreBound(y);

      if (oldViewX != -1 && oldViewY != -1) {
        g.drawLine(oldViewX, oldViewY, viewX, viewY);
      }
      oldViewX = viewX;
      oldViewY = viewY;
    }
  }

}
