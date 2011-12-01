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
package org.mklab.graph.g2d;

import org.mklab.abgr.Color;
import org.mklab.abgr.Graphics;
import org.mklab.graph.figure.Canvas;
import org.mklab.graph.figure.CanvasListener;
import org.mklab.graph.figure.CanvasListenerList;
import org.mklab.graph.figure.ContainerFigureImpl;
import org.mklab.graph.figure.Figures;
import org.mklab.graph.figure.Point;
import org.mklab.graph.figure.TextFigure;
import org.mklab.graph.g2d.model.DataModelListener;
import org.mklab.graph.g2d.model.GraphModel;
import org.mklab.graph.g2d.model.GridType;
import org.mklab.graph.g2d.model.LineModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * 二次元グラフを表すクラスです。
 * <p>
 * このクラスは、基本的なグラフ機能を備えたクラスです。<br>
 * グラフのタイトル、x軸、y軸のタイトル、ユーザー通知領域として利用できるステータスバーを提供します。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public class GraphFigure extends ContainerFigureImpl implements HasCoordinateSpace, CanvasListener {

  /** ステータス描画を行う図です。 */
  private TextFigure statusBar;
  /** 座標系の図です。 */
  private BaseGraphFigure baseGraphFigure;
  /** グラフのモデルです。 */
  private GraphModel model;
  /** 描画対象のキャンバスです。 */
  private CanvasWrapper canvas;

  /**
   * {@link GraphFigure}オブジェクトを構築します。
   */
  public GraphFigure() {
    this.canvas = new CanvasWrapper();
    this.canvas.addCanvasListener(this);
    this.model = createGraphModel();

    this.statusBar = new TextFigure();
    final InfoBoxFigure infoBox = new InfoBoxFigure(this.model.getDataModel());
    final CoordinateSpaceFigure baseCoordinateSpace = new CoordinateSpaceFigure(this.canvas, infoBox);
    this.baseGraphFigure = new BaseGraphFigure(baseCoordinateSpace);

    add(this.statusBar);
    add(this.baseGraphFigure);

    setBackgroundColor(ColorConstants.BACKGROUND);
    this.baseGraphFigure.getCoordinateSpace().getGrid().setBackgroundColor(ColorConstants.COORDINATES_BACKGROUND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layout(Graphics g) {
    final int statusBarHeight = g.getTextHeight();
    final int rightSpace = 10;

    this.baseGraphFigure.setBounds(0, 0, getWidth() - rightSpace, getHeight() - statusBarHeight);
    this.statusBar.setBounds(0, getHeight() - statusBarHeight, getWidth(), statusBarHeight);

    validateChildren(g);
  }

  private GraphModel createGraphModel() {
    final GraphModel m = new GraphModel();
    m.addPropertyChangeListener(new PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        final Object value = evt.getNewValue();

        if (GraphModel.TITLE_PROPERTY_NAME.equals(propertyName)) {
          setTitle((String)value);
        } else if (GraphModel.X_AXIS_NAME_PROPERTY_NAME.equals(propertyName)) {
          setXAxisName((String)value);
        } else if (GraphModel.GRID_TYPE_X_PROPERTY_NAME.equals(propertyName)) {
          setGridTypeX((GridType)value);
        } else if (GraphModel.Y_AXIS_NAME_PROPERTY_NAME.equals(propertyName)) {
          setYAxisName((String)value);
        } else if (GraphModel.GRID_TYPE_Y_PROPERTY_NAME.equals(propertyName)) {
          setGridTypeY((GridType)value);
        } else if (GraphModel.BACKGROUND_COLOR_PROPERTY_NAME.equals(propertyName)) {
          setBackgroundColor((Color)value);
        } else if (GraphModel.GRID_BACKGROUND_COLOR_PROPERTY_NAME.equals(propertyName)) {
          getCoordinateSpace().getGrid().setBackgroundColor((Color)value);
        } else if (GraphModel.GRID_BORDER_COLOR_PROPERTY_NAME.equals(propertyName)) {
          getCoordinateSpace().setBorderColor((Color)value);
        } else if (GraphModel.GRID_COLOR_PROPERTY_NAME.equals(propertyName)) {
          getCoordinateSpace().getGrid().setGridColor((Color)evt.getNewValue());
        } else if (GraphModel.MINOR_GRID_COLOR_PROPERTY_NAME.equals(propertyName)) {
          getCoordinateSpace().getGrid().setMinorGridColor((Color)evt.getNewValue());
        } else if (GraphModel.GRID_ENABLED_PROPERTY_NAME.equals(propertyName)) {
          getCoordinateSpace().getGrid().setGridEnabled(((Boolean)evt.getNewValue()).booleanValue());
        } else if (GraphModel.MINOR_GRID_ENABLED_PROPERTY_NAME.equals(propertyName)) {
          getCoordinateSpace().getGrid().setMinorGridEnabled(((Boolean)evt.getNewValue()).booleanValue());
        } else if (GraphModel.INFO_BOX_BACKGROUND_COLOR_PROPERTY_NAME.equals(propertyName)) {
          getCoordinateSpace().getInfoBox().setBackgroundColor((Color)value);
        } else if (GraphModel.FOREGROUND_COLOR_PROPERTY_NAME.equals(propertyName)) {
          setForegroundColor((Color)value);
        }
      }
    });
    m.getDataModel().addDataModelListener(new DataModelListener() {

      @Override
      public void lineModelRemoved(LineModel lineModel) {
        getCoordinateSpace().removeLine(lineModel);
      }

      @Override
      public void lineModelAdded(LineModel lineModel) {
        getCoordinateSpace().addLine(lineModel);
      }
    });
    return m;
  }

  void setGridTypeX(GridType gridType) {
    getCoordinateSpace().getGrid().setGridFactoryX(createGridFactory(gridType));
  }

  void setGridTypeY(GridType gridType) {
    getCoordinateSpace().getGrid().setGridFactoryY(createGridFactory(gridType));
  }

  private static GridFactory createGridFactory(GridType type) {
    switch (type) {
      case DEFAULT:
        return new StandardGridFactory();
      case LOG:
        return new LogScaleGridFactory();
    }
    throw new UnsupportedOperationException();
  }

  CoordinateSpaceFigure getCoordinateSpace() {
    return this.baseGraphFigure.getCoordinateSpace();
  }

  BaseGraphFigure getBaseGraphFigure() {
    return this.baseGraphFigure;
  }

  void setForegroundColor(Color foregroundColor) {
    this.baseGraphFigure.setForegroundColor(foregroundColor);
  }

  void setTitle(String title) {
    this.baseGraphFigure.setTitle(title);
    invalidate();
  }

  void setXAxisName(String label) {
    this.baseGraphFigure.setXAxisName(label);
    invalidate();
  }

  void setYAxisName(String label) {
    this.baseGraphFigure.setYAxisName(label);
    invalidate();
  }

  /**
   * グラフモデルを取得します。
   * 
   * @return グラフモデル
   */
  public GraphModel getModel() {
    return this.model;
  }

  /**
   * 表示範囲を設定します。
   * 
   * @param startX 開始x値
   * @param endX 終了x値
   * @param startY 開始y値
   * @param endY 終了y値
   */
  public void setScope(double startX, double endX, double startY, double endY) {
    this.baseGraphFigure.getCoordinateSpace().setScope(new Scope(startX, endX, startY, endY));
  }

  /**
   * ステータスメッセージを設定します。
   * 
   * @param msg ステータスメッセージ
   */
  public void setStatus(String msg) {
    this.statusBar.setText(msg);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveScope(final int dx, final int dy) {
    this.baseGraphFigure.moveScope(dx, dy);
    invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void scaleScope(final int x, final int y, double ratio) {
    Util.scaleScope(this, this.baseGraphFigure, x, y, ratio);
    invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModelX(int x) {
    return Util.viewToModelX(this, this.baseGraphFigure.getCoordinateSpace().getGrid(), x);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModelY(int y) {
    return Util.viewToModelY(this, this.baseGraphFigure.getCoordinateSpace().getGrid(), y);
  }

  /**
   * ビュー上の与えられた座標が、座標空間の領域に含まれるかどうか調べます。
   * 
   * @param x x座標
   * @param y y座標
   * @return 含まれていればtrue,そうでなければfalse
   */
  public boolean containsInCoordinateSpace(int x, int y) {
    final GridFigure gridFigure = this.baseGraphFigure.getCoordinateSpace().getGrid();
    final Point pointOnGrid = Figures.convertPoint(this, gridFigure, new Point(x, y));

    return gridFigure.contains(pointOnGrid.x, pointOnGrid.y);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setScope(Scope scope) {
    this.baseGraphFigure.setScope(scope);
  }

  /**
   * 描画対象のキャンバスを設定します。
   * 
   * @param canvas キャンバス
   */
  public void setCanvas(Canvas canvas) {
    this.canvas.setCanvas(canvas);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void canvasSizeChanged() {
    final int w = this.canvas.getCanvasWidth();
    final int h = this.canvas.getCanvasHeight();

    setSize(w, h);
    this.canvas.redrawCanvas();
  }

  /**
   * キャンバスをラップするクラスです。
   * <p>
   * キャンバスの遅延初期化の際に、まだ初期化されていなくても一旦キャンバスインスタンスを子の図に設定するために必要とされます。 リスナー関連の処理以外は、
   * {@link #setCanvas(Canvas)}を行うまで実行することができません。
   * 
   * @author Yuhi Ishikura
   */
  static class CanvasWrapper implements Canvas, CanvasListener {

    private Canvas canvas;
    private CanvasListenerList listeners = new CanvasListenerList();

    void setCanvas(Canvas canvas) {
      if (this.canvas != null) {
        this.canvas.removeCanvasListener(this);
      }
      this.canvas = canvas;
      this.canvas.addCanvasListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCanvasWidth() {
      checkCanvasIsSet();
      return this.canvas.getCanvasWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCanvasHeight() {
      checkCanvasIsSet();
      return this.canvas.getCanvasHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redrawCanvas() {
      checkCanvasIsSet();
      this.canvas.redrawCanvas();
    }

    private void checkCanvasIsSet() {
      if (this.canvas == null) throw new IllegalStateException("Invalid graph state.  Canvas not set yet."); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCanvasListener(CanvasListener canvasListener) {
      this.listeners.remove(canvasListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCanvasListener(CanvasListener canvasListener) {
      this.listeners.add(canvasListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void canvasSizeChanged() {
      this.listeners.fireCanvasSizeChanged();
    }

  }
}
