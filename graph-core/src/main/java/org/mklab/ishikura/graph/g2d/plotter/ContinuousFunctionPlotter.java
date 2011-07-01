/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.g2d.GridFigure;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
class ContinuousFunctionPlotter implements Plotter {

  private Function2D function;

  /**
   * {@link ContinuousFunctionPlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  ContinuousFunctionPlotter(Function2D function) {
    super();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    final int width = grid.getWidth();
    int lastViewY = -1;
    for (int viewX = 0; viewX < width; viewX++) {
      final double modelX = grid.viewToModelX(viewX);
      final double modelY = this.function.evalY(modelX);
      final int viewY = grid.modelToViewYIgnoreBound(modelY);
      if (lastViewY != -1) g.drawLine(viewX - 1, lastViewY, viewX, viewY);
      lastViewY = viewY;
    }
  }

}
