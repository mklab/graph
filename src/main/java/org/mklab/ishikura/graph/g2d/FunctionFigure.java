/*
 * Created on 2010/10/16
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.AbstractFigure;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.function.Point2D;
import org.mklab.ishikura.graph.function.DiscreteFunction2D;
import org.mklab.ishikura.graph.function.TraversableIterator;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;
import org.mklab.ishikura.graph.graphics.LineType;


/**
 * 関数の描画を行う図形です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/16
 */
public class FunctionFigure extends AbstractFigure {

  /** 描画する関数です。 */
  private Function2D function;
  /** 座標系です。描画時の座標計算に利用します。 */
  private GridFigure grid;
  /** 線の色です。 */
  private Color lineColor = Color.RED;
  /** 線の太さです。 */
  private int lineWidth = 1;
  /** 線種です。 */
  private LineType lineType = LineType.DEFAULT;
  /** 関数名です。 */
  private String lineName;

  /**
   * {@link FunctionFigure}オブジェクトを構築します。
   * 
   * @param grid グラフの格子
   */
  FunctionFigure(GridFigure grid) {
    if (grid == null) throw new NullPointerException();
    this.grid = grid;
  }

  /**
   * 線の色を取得します。
   * 
   * @return 線の色
   */
  public Color getLineColor() {
    return this.lineColor;
  }

  /**
   * 線の描画色を設定します。
   * 
   * @param lineColor 線の色
   */
  public void setLineColor(Color lineColor) {
    this.lineColor = lineColor;
    invalidate();
  }

  /**
   * 線種を取得します。
   * 
   * @return 線種
   */
  public LineType getLineType() {
    return this.lineType;
  }

  /**
   * 線種を設定します。
   * 
   * @param lineType 線種
   */
  public void setLineType(LineType lineType) {
    if (lineType == null) throw new NullPointerException();
    this.lineType = lineType;
    invalidate();
  }

  /**
   * 線の名前を取得します。
   * 
   * @return lineName
   */
  public String getLineName() {
    return this.lineName;
  }

  /**
   * 線の名前を設定します。
   * 
   * @param lineName 線の名前
   */
  public void setLineName(String lineName) {
    this.lineName = lineName;
    invalidate();
  }

  /**
   * 線の幅を取得します。
   * 
   * @return 線の幅
   */
  public int getLineWidth() {
    return this.lineWidth;
  }

  /**
   * 線幅を設定します。
   * 
   * @param lineWidth 線幅
   */
  public void setLineWidth(int lineWidth) {
    if (lineWidth <= 0) throw new IllegalArgumentException();
    this.lineWidth = lineWidth;
  }

  /**
   * 関数を設定します。
   * 
   * @param function function
   */
  public void setFunction(Function2D function) {
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(int x, int y) {
    if (this.function == null) return false;
    if (super.contains(x, y) == false) return false;

    final double modelX = this.grid.viewToModelX(x);
    final double actualModelY = this.function.evalY(modelX);
    final int actualViewY = this.grid.modelToViewY(this, actualModelY);

    if (actualViewY == -1) return false;

    return Math.abs(actualViewY - y) < 5;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    if (this.function == null) return;

    final Color oldColor = g.getColor();
    final float oldLineWidth = g.getLineWidth();
    final LineType oldLineType = g.getLineType();

    g.setColor(this.lineColor);
    g.setLineWidth(this.lineWidth);
    g.setLineType(this.lineType);
    if (this.function instanceof DiscreteFunction2D) {
      final Scope scope = this.grid.getScope();
      drawDiscreteFunction(g, scope, this.function);
    } else {
      drawContinuousFunction(g, this.function);
    }
    g.setLineType(oldLineType);
    g.setLineWidth(oldLineWidth);
    g.setColor(oldColor);
  }

  /**
   * 連続関数の描画を行います。
   * 
   * @param g 描画対象グラフィックスコンテキスト
   * @param f 描画する関数
   */
  private void drawContinuousFunction(Graphics g, Function2D f) {
    final int width = getWidth();
    int lastViewY = -1;
    for (int viewX = 0; viewX < width; viewX++) {
      final double modelX = this.grid.viewToModelX(viewX);
      final double modelY = f.evalY(modelX);
      final int viewY = this.grid.modelToViewYIgnoreBound(modelY);
      if (lastViewY != -1) g.drawLine(viewX - 1, lastViewY, viewX, viewY);
      lastViewY = viewY;
    }
  }

  /**
   * 離散関数の描画を行います。
   * 
   * @param g 描画対象のグラフィックスコンテキスト
   * @param scope 描画範囲
   * @param itr 描画する点のイテレータ
   */
  private void drawDiscreteFunction(Graphics g, final Scope scope, final Function2D f) {
    final TraversableIterator<Point2D> itr = ((DiscreteFunction2D)f).iterator(scope.getX().getStart());
    if (itr.hasNext() == false) return;

    Point2D lastPoint = itr.next();
    while (itr.hasNext()) {
      final Point2D currentPoint = itr.next();
      int x1 = this.grid.modelToViewXIgnoreBound(lastPoint.getX());
      int y1 = this.grid.modelToViewYIgnoreBound(lastPoint.getY());
      int x2 = this.grid.modelToViewXIgnoreBound(currentPoint.getX());
      int y2 = this.grid.modelToViewYIgnoreBound(currentPoint.getY());
      g.drawLine(x1, y1, x2, y2);

      lastPoint = currentPoint;
      if (currentPoint.getX() > scope.getX().getEnd()) break;
      if (currentPoint.getY() > scope.getY().getEnd()) continue;
    }
  }

}
