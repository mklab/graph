/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

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
    final int divisionCount = 1000;
    final double dth = 2 * Math.PI / divisionCount;
    int oldViewX = -1;
    int oldViewY = -1;
    for (int i = 0; i < divisionCount; i++) {
      final double theta = dth * i;
      final double r = this.function.evalR(theta);

      final double x = r * Math.cos(theta);
      final double y = r * Math.sin(theta);
      final int viewX = grid.modelToViewX(x);
      final int viewY = grid.modelToViewY(y);

      if (oldViewX != -1 && oldViewY != -1 && viewX != -1 && viewY != -1) {
        g.drawLine(oldViewX, oldViewY, viewX, viewY);
      }
      oldViewX = viewX;
      oldViewY = viewY;
    }
  }

}
