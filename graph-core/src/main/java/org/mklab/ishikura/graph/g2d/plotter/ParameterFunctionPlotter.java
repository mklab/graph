/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import java.util.Iterator;

import org.mklab.ishikura.graph.function.ParameterFunction2D;
import org.mklab.ishikura.graph.g2d.GridFigure;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 媒介変数表示の関数を描画するクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public class ParameterFunctionPlotter implements Plotter {

  ParameterFunction2D function;

  /**
   * {@link ParameterFunctionPlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  ParameterFunctionPlotter(ParameterFunction2D function) {
    if (function == null) throw new NullPointerException();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    final Iterator<Double> tSeries = this.function.provideParameter();

    int oldViewX = -1;
    int oldViewY = -1;
    while (tSeries.hasNext()) {
      final double t = tSeries.next().doubleValue();
      final double modelX = this.function.evalX(t);
      final double modelY = this.function.evalY(t);
      if (Plotters.isValidNumber(modelX) == false || Plotters.isValidNumber(modelY) == false) continue;

      final int viewX = grid.modelToViewXIgnoreBound(modelX);
      final int viewY = grid.modelToViewYIgnoreBound(modelY);
      if (oldViewX != -1 && oldViewY != -1) {
        g.drawLine(oldViewX, oldViewY, viewX, viewY);
      }
      oldViewX = viewX;
      oldViewY = viewY;
    }
  }

}
