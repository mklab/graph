/*
 * Created on 2010/10/16
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.AbstractFigure;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.g2d.plotter.DefaultPlotterFactory;
import org.mklab.ishikura.graph.g2d.plotter.Plotter;
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
  private Color lineColor = ColorConstants.FUNCTION_LINE;
  /** 線の太さです。 */
  private int lineWidth = 1;
  /** 線種です。 */
  private LineType lineType = LineType.DEFAULT;
  /** 関数名です。 */
  private String lineName;
  /** 関数描画を行うプロッターです。 */
  private Plotter plotter;

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
    this.plotter = new DefaultPlotterFactory().create(function);
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
    this.plotter.plot(g, this.grid);
    g.setLineType(oldLineType);
    g.setLineWidth(oldLineWidth);
    g.setColor(oldColor);
  }

}
