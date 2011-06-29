package org.mklab.ishikura.graph.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.mklab.ishikura.graph.g2d.GraphFigure;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * SWTによるグラフ描画キャンバスです。
 * 
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
public class GraphCanvas extends Canvas {

  private GraphFigure graphFigure;

  /**
   * {@link GraphCanvas}オブジェクトを構築します。
   * 
   * @param parent 親コンポジット
   */
  public GraphCanvas(Composite parent) {
    super(parent, SWT.NULL);

    addPaintListener(new PaintListener() {

      @Override
      public void paintControl(PaintEvent arg0) {
        draw(arg0.gc);
      }
    });

    addControlListener(new ControlAdapter() {

      /**
       * {@inheritDoc}
       */
      @Override
      public void controlResized(@SuppressWarnings("unused") ControlEvent e) {
        fitFigure();
      }
    });

    final GraphCanvasMouseListener l = new GraphCanvasMouseListener(this);
    addMouseListener(l);
    addMouseMoveListener(l);
    addMouseWheelListener(l);
  }

  void fitFigure() {
    if (this.graphFigure == null) return;

    final Point size = getSize();
    this.graphFigure.setSize(size.x, size.y);
  }

  GraphFigure getGraphFigure() {
    return this.graphFigure;
  }

  /**
   * 表示するグラフを設定します。
   * 
   * @param graphFigure グラフ
   */
  public void setGraphFigure(GraphFigure graphFigure) {
    this.graphFigure = graphFigure;
  }

  void draw(GC gc) {
    final Graphics g = new SwtGraphics(gc);
    final Point size = getSize();
    g.fillRect(0, 0, size.x, size.y);

    if (this.graphFigure == null) return;
    this.graphFigure.draw(g);
  }
}
