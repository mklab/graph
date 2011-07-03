package org.mklab.ishikura.graph.swt;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.graphics.Point;
import org.mklab.ishikura.graph.g2d.GraphFigure;
import org.mklab.ishikura.graph.g2d.HasCoordinateSpace;


/**
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
class GraphCanvasMouseListener implements MouseListener, MouseMoveListener, MouseWheelListener {

  private boolean pressed = false;
  private Point lastMousePoint;
  private GraphCanvas graphCanvas;

  /**
   * {@link GraphCanvasMouseListener}オブジェクトを構築します。
   * 
   * @param graphCanvas
   */
  GraphCanvasMouseListener(GraphCanvas graphCanvas) {
    super();
    this.graphCanvas = graphCanvas;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mouseMove(MouseEvent e) {
    updateStatusArea(e);
    moveScope(e);
    this.graphCanvas.redraw();
  }

  private void moveScope(MouseEvent e) {
    if (this.pressed == false) return;
    if (isFigureSet() == false) return;

    final Point currentMousePoint = new Point(e.x, e.y);
    final int dx = this.lastMousePoint.x - currentMousePoint.x;
    final int dy = this.lastMousePoint.y - currentMousePoint.y;
    final HasCoordinateSpace graph = this.graphCanvas.getGraphFigure();
    graph.moveScope(dx, -dy);
    this.graphCanvas.redraw();

    this.lastMousePoint = currentMousePoint;
  }

  private void updateStatusArea(MouseEvent e) {
    if (isFigureSet() == false) return;
    final GraphFigure graph = this.graphCanvas.getGraphFigure();

    if (graph.containsInCoordinateSpace(e.x, e.y) == false) {
      graph.setStatus(null);
    } else {
      final double x = graph.viewToModelX(e.x);
      final double y = graph.viewToModelY(e.y);
      graph.setStatus(String.format("(x,y) = (%5.3f,%5.3f)", Double.valueOf(x), Double.valueOf(y))); //$NON-NLS-1$
    }
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unused")
  @Override
  public void mouseDoubleClick(MouseEvent e) {
    if (isFigureSet() == false) return;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mouseDown(MouseEvent e) {
    this.pressed = true;
    this.lastMousePoint = new Point(e.x, e.y);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unused")
  @Override
  public void mouseUp(MouseEvent e) {
    this.pressed = false;
    this.lastMousePoint = null;
  }

  private boolean isFigureSet() {
    return this.graphCanvas.getGraphFigure() != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void mouseScrolled(MouseEvent e) {
    if (isFigureSet() == false) return;
    final HasCoordinateSpace graph = this.graphCanvas.getGraphFigure();

    final boolean zooming = e.count > 0;
    graph.scaleScope(e.x, e.y, zooming ? 1.2 : 0.8);
    this.graphCanvas.redraw();
  }

}
