/*
 * Created on 2010/10/16
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.abgr.Color;
import org.mklab.abgr.Graphics;
import org.mklab.abgr.LineType;
import org.mklab.ishikura.graph.figure.AbstractFigure;
import org.mklab.ishikura.graph.g2d.model.LineModel;
import org.mklab.ishikura.graph.g2d.plotter.DefaultPlotterFactory;
import org.mklab.ishikura.graph.g2d.plotter.Plotter;


/**
 * 関数の描画を行う図形です。
 * <p>
 * 関数の描画に用いる線のスタイルの決定はこのクラスの責任ですが、関数自身の描画は {@link Plotter}に移譲 しています。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/16
 */
public class FunctionFigure extends AbstractFigure {

  /** 関数包含関係チェックの許容誤差です。 */
  private static final int DELTA = 5;
  /** この図のモデルです。 */
  private LineModel lineModel;
  /** 座標系です。描画時の座標計算に利用します。 */
  private GridFigure grid;
  /** 関数描画を行うプロッターです。 */
  private Plotter plotter;

  /**
   * {@link FunctionFigure}オブジェクトを構築します。
   * 
   * @param grid グラフの格子
   */
  FunctionFigure(GridFigure grid, LineModel lineModel) {
    if (grid == null) throw new NullPointerException("grid == null"); //$NON-NLS-1$
    if (lineModel == null) throw new NullPointerException("lineModel == null"); //$NON-NLS-1$
    this.lineModel = lineModel;
    this.grid = grid;

    this.plotter = new DefaultPlotterFactory().create(lineModel.getFunction());
  }

  /**
   * 線のモデルを取得します。
   * 
   * @return lineModel
   */
  public LineModel getLineModel() {
    return this.lineModel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(int x, int y) {
    if (super.contains(x, y) == false) return false;

    return this.lineModel.getFunction().contains(x, y, DELTA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    final Color oldColor = g.getColor();
    final float oldLineWidth = g.getLineWidth();
    final LineType oldLineType = g.getLineType();

    g.setColor(this.lineModel.getLineColor());
    g.setLineWidth(this.lineModel.getLineWidth());
    g.setLineType(this.lineModel.getLineType());
    this.plotter.plot(g, this.grid);
    g.setLineType(oldLineType);
    g.setLineWidth(oldLineWidth);
    g.setColor(oldColor);
  }

}
