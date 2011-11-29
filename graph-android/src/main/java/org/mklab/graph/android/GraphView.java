package org.mklab.graph.android;

import org.mklab.abgr.Graphics;
import org.mklab.abgr.android.AndroidGraphics;
import org.mklab.graph.figure.CanvasListener;
import org.mklab.graph.figure.CanvasListenerList;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.HasCoordinateSpace;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;


/**
 * {@link GraphFigure}を描画する{@link View}です。
 * 
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
public class GraphView extends View implements org.mklab.graph.figure.Canvas {

  private GraphFigure graphFigure;
  CanvasListenerList canvasListenerList = new CanvasListenerList();

  /**
   * {@link GraphView}オブジェクトを構築します。
   * 
   * @param context コンテキスト
   */
  public GraphView(Context context) {
    super(context);
    setFocusable(true);
    setOnTouchListener(new GraphViewTouchListener());
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unused")
  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    this.canvasListenerList.fireCanvasSizeChanged();
  }

  HasCoordinateSpace getGraphFigure() {
    return this.graphFigure;
  }

  /**
   * 描画するグラフを設定します。
   * 
   * @param graphFigure 描画するグラフ
   */
  public void setGraphFigure(GraphFigure graphFigure) {
    this.graphFigure = graphFigure;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void onDraw(Canvas canvas) {
    final Graphics g = new AndroidGraphics(canvas);
    g.fillRect(0, 0, getWidth(), getHeight());
    if (this.graphFigure == null) return;

    this.graphFigure.setWidth(getWidth());
    this.graphFigure.setHeight(getHeight());

    this.graphFigure.draw(g);
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
    invalidate();
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
