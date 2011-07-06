/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import org.mklab.ishikura.graph.function.ContinuousFunction2D;
import org.mklab.ishikura.graph.g2d.GridFigure;
import org.mklab.ishikura.graph.graphics.Graphics;


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
    final int[] pointArray = new int[width * 2];
    int pointer = 0;
    for (int viewX = 0; viewX < width; viewX++) {
      final double modelX = grid.viewToModelX(viewX);
      final double modelY = this.function.evalY(modelX);
      if (Plotters.isValidNumber(modelY) == false) continue;

      final int viewY = grid.modelToViewYIgnoreBound(modelY);

      final boolean isOutOfRange = viewY < -grid.getHeight() || viewY > grid.getHeight() * 2;
      if (isOutOfRange) {
        g.drawPolyline(subarray(pointArray, 2 * pointer));
        pointer = 0;
      } else {
        pointArray[2 * pointer] = viewX;
        pointArray[2 * pointer + 1] = viewY;
        pointer++;
      }
    }
    if (pointer > 0) {
      g.drawPolyline(subarray(pointArray, 2 * pointer));
    }
  }

  private int[] subarray(int[] array, int size) {
    int[] n = new int[size];
    System.arraycopy(array, 0, n, 0, size);
    return n;
  }

}
