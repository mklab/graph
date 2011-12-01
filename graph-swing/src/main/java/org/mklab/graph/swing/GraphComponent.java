/*
 * Copyright (C) 2011 Koga Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Created on 2010/10/18
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.graph.swing;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import org.mklab.abgr.awt.AwtGraphics;
import org.mklab.graph.figure.Canvas;
import org.mklab.graph.figure.CanvasListener;
import org.mklab.graph.figure.CanvasListenerList;
import org.mklab.graph.g2d.GraphFigure;


/**
 * Swingによるグラフ表示コンポーネントです。
 * <p>
 * マウス操作で移動・拡大縮小を行うことができます。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/18
 */
public class GraphComponent extends JComponent implements Canvas {

  private static final long serialVersionUID = -2072147966373187572L;
  private GraphFigure graph;
  CanvasListenerList canvasListenerList = new CanvasListenerList();

  /**
   * 新しく生成された<code>GraphComponent</code>オブジェクトを初期化します。
   */
  public GraphComponent() {
    final MouseAdapter mouseListener = new GraphMouseHandler();
    addMouseMotionListener(mouseListener);
    addMouseListener(mouseListener);
    addMouseWheelListener(mouseListener);
    addComponentListener(new ComponentAdapter() {

      /**
       * {@inheritDoc}
       */
      @Override
      public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        GraphComponent.this.canvasListenerList.fireCanvasSizeChanged();
      }
    });

    setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
  }

  /**
   * 表示するグラフを設定します。
   * 
   * @param graph 表示するグラフ
   */
  public void setGraph(GraphFigure graph) {
    if (graph == null) throw new NullPointerException();
    this.graph = graph;
  }

  /**
   * @return graphを返します。
   */
  public GraphFigure getGraph() {
    return this.graph;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void paintComponent(Graphics g) {
    final int w = this.getWidth();
    final int h = this.getHeight();

    this.graph.setSize(w, h);
    g.clearRect(0, 0, w, h);

    final Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    final AwtGraphics graphG = new AwtGraphics(g2);
    this.graph.draw(graphG);
  }

  /**
   * @author Yuhi Ishikura
   * @version $Revision$, 2010/10/18
   */
  final class GraphMouseHandler extends MouseAdapter {

    private Point pressedPoint;

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
      this.pressedPoint = e.getPoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      final Point p = e.getPoint();
      updateMousePositionStatus(e, p);
    }

    private void updateMousePositionStatus(@SuppressWarnings("unused") MouseEvent e, Point p) {
      final GraphFigure g = getGraph();
      if (g.containsInCoordinateSpace(p.x, p.y) == false) {
        g.setStatus(null);
      } else {
        final double x = g.viewToModelX(p.x);
        final double y = g.viewToModelY(p.y);
        g.setStatus(String.format("(x,y) = (%5.3f,%5.3f)", Double.valueOf(x), Double.valueOf(y))); //$NON-NLS-1$
      }
      repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (this.pressedPoint == null) return;
      if (SwingUtilities.isRightMouseButton(e)) return;

      final Point point = e.getPoint();
      final int dx = this.pressedPoint.x - point.x;
      final int dy = this.pressedPoint.y - point.y;
      this.pressedPoint = point;

      getGraph().moveScope(dx, -dy);
      GraphComponent.this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      if (SwingUtilities.isRightMouseButton(e) == false) return;

      final Point releasedPoint = e.getPoint();
      final double x1 = getGraph().viewToModelX(releasedPoint.x);
      final double y1 = getGraph().viewToModelY(releasedPoint.y);
      final double x2 = getGraph().viewToModelX(this.pressedPoint.x);
      final double y2 = getGraph().viewToModelY(this.pressedPoint.y);
      getGraph().setScope(Math.min(x1, x2), Math.max(x1, x2), Math.min(y1, y2), Math.max(y1, y2));
      GraphComponent.this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
      final Point p = e.getPoint();

      final boolean isExpanding = e.getWheelRotation() < 0;

      getGraph().scaleScope(p.x, p.y, isExpanding ? 1.2 : 0.8);
      GraphComponent.this.repaint();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCanvasWidth() {
    return getWidth();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCanvasHeight() {
    return getHeight();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redrawCanvas() {
    repaint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addCanvasListener(CanvasListener canvasListener) {
    this.canvasListenerList.add(canvasListener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeCanvasListener(CanvasListener canvasListener) {
    this.canvasListenerList.remove(canvasListener);
  }
}
