/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import org.mklab.abgr.Graphics;
import org.mklab.ishikura.graph.function.DiscreteFunction2D;
import org.mklab.ishikura.graph.function.Point2D;
import org.mklab.ishikura.graph.function.TraversableIterator;
import org.mklab.ishikura.graph.g2d.GridFigure;
import org.mklab.ishikura.graph.g2d.Scope;


/**
 * {@link DiscreteFunction2D}のグラフを描画するクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
class DiscreteFunctionPlotter implements Plotter {

  DiscreteFunction2D function;

  /**
   * {@link DiscreteFunctionPlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  DiscreteFunctionPlotter(DiscreteFunction2D function) {
    super();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    final Scope scope = grid.getScope();
    final TraversableIterator<Point2D> itr = this.function.iterator(scope.getX().getStart());
    if (itr.hasNext() == false) return;

    Point2D lastPoint = itr.next();
    while (itr.hasNext()) {
      final Point2D currentPoint = itr.next();
      if (Plotters.isValidNumber(currentPoint.getX()) == false || Plotters.isValidNumber(currentPoint.getY()) == false) continue;

      int x1 = grid.modelToViewXIgnoreBound(lastPoint.getX());
      int y1 = grid.modelToViewYIgnoreBound(lastPoint.getY());
      int x2 = grid.modelToViewXIgnoreBound(currentPoint.getX());
      int y2 = grid.modelToViewYIgnoreBound(currentPoint.getY());
      g.drawLine(x1, y1, x2, y2);

      lastPoint = currentPoint;
      if (currentPoint.getX() > scope.getX().getEnd()) break;
      if (currentPoint.getY() > scope.getY().getEnd()) continue;
    }
  }

}
