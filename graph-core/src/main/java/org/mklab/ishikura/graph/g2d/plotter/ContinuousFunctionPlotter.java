/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import org.mklab.abgr.Graphics;
import org.mklab.ishikura.graph.function.ContinuousFunction2D;
import org.mklab.ishikura.graph.g2d.GridFigure;


/**
 * 連続時間の関数を描画するクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
class ContinuousFunctionPlotter implements Plotter {

  private ContinuousFunction2D function;

  /**
   * {@link ContinuousFunctionPlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  ContinuousFunctionPlotter(ContinuousFunction2D function) {
    super();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    final int width = grid.getWidth();
    final PolylineRenderer r = new PolylineRenderer(grid);
    for (int viewX = 0; viewX < width; viewX++) {
      final double modelX = grid.viewToModelX(viewX);
      final double modelY = this.function.evalY(modelX);
      if (Plotters.isValidNumber(modelY) == false) continue;

      final int viewY = grid.modelToViewYIgnoreBound(modelY);
      r.appendPoint(viewX, viewY);
    }
    r.draw(g);
  }

}
