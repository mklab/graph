/*
 * Created on 2010/10/18
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.swing;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JComponent;

import org.mklab.ishikura.graph.g2d.GraphFigure;


/**
 * Swingによるグラフ表示コンポーネントです。
 * <p>
 * マウス操作で移動・拡大縮小を行うことができます。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/18
 */
public class GraphComponent extends JComponent {

  private static final long serialVersionUID = -2072147966373187572L;
  private GraphFigure graph;

  /**
   * 新しく生成された<code>GraphComponent</code>オブジェクトを初期化します。
   */
  public GraphComponent() {
    final MouseAdapter mouseListener = new GraphMouseHandler();
    this.addMouseMotionListener(mouseListener);
    this.addMouseListener(mouseListener);
    this.addMouseWheelListener(mouseListener);

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

    private Point lastClickPoint;

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
      this.lastClickPoint = e.getPoint();
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
      if (this.lastClickPoint == null) return;

      final Point point = e.getPoint();
      final int dx = this.lastClickPoint.x - point.x;
      final int dy = this.lastClickPoint.y - point.y;
      this.lastClickPoint = point;

      getGraph().move(dx, -dy);
      GraphComponent.this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
      final Point p = e.getPoint();

      final boolean isExpanding = e.getWheelRotation() < 0;

      getGraph().scale(p.x, p.y, isExpanding ? 1.2 : 0.8);
      GraphComponent.this.repaint();
    }
  }
}
