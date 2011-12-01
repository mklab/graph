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
package org.mklab.graph.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.mklab.abgr.Graphics;
import org.mklab.abgr.swt.SwtGraphics;
import org.mklab.graph.figure.CanvasListener;
import org.mklab.graph.figure.CanvasListenerList;
import org.mklab.graph.g2d.GraphFigure;


/**
 * SWTによるグラフ描画キャンバスです。
 * 
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
public class GraphCanvas extends Canvas implements org.mklab.graph.figure.Canvas {

  private GraphFigure graphFigure;
  CanvasListenerList canvasListenerList = new CanvasListenerList();

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

    addControlListener(new ControlAdapter() {

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("unused")
      @Override
      public void controlResized(ControlEvent e) {
        GraphCanvas.this.canvasListenerList.fireCanvasSizeChanged();
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

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCanvasWidth() {
    return getSize().x;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCanvasHeight() {
    return getSize().y;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redrawCanvas() {
    redraw();
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
