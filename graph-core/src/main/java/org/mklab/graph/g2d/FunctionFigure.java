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
import org.mklab.abgr.LineType;
import org.mklab.graph.figure.AbstractFigure;
import org.mklab.graph.figure.Canvas;
import org.mklab.graph.function.Function2D;
import org.mklab.graph.function.realtime.EffectiveCanvasUpdater;
import org.mklab.graph.function.realtime.PeriodicalCanvasUpdaterWatcher;
import org.mklab.graph.function.realtime.RealtimeFunction;
import org.mklab.graph.g2d.model.LineModel;
import org.mklab.graph.g2d.plotter.Plotter;


/**
 * 関数の描画を行う図形です。
 * <p>
 * 関数の描画に用いる線のスタイルの決定はこのクラスの責任ですが、関数自身の描画は {@link Plotter}に移譲 しています。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/16
 */
public class FunctionFigure extends AbstractFigure {

  private static final PeriodicalCanvasUpdaterWatcher updaterWatcher = new PeriodicalCanvasUpdaterWatcher();

  /** 関数包含関係チェックの許容誤差です。 */
  private static final int DELTA = 5;
  /** この図のモデルです。 */
  private LineModel lineModel;
  /** 座標系です。描画時の座標計算に利用します。 */
  private GridFigure grid;
  /** 関数描画を行うプロッターです。 */
  private Plotter plotter;
  /** キャンバスの更新を行うオブジェクトです。 */
  private EffectiveCanvasUpdater canvasUpdater;
  /** リアルタイム描画モードであるかどうかです。 */
  private boolean isRealtime = false;

  FunctionFigure(Canvas canvas, GridFigure grid, LineModel lineModel) {
    if (canvas == null) throw new NullPointerException("canvas == null"); //$NON-NLS-1$
    if (grid == null) throw new NullPointerException("grid == null"); //$NON-NLS-1$
    if (lineModel == null) throw new NullPointerException("lineModel == null"); //$NON-NLS-1$

    this.canvasUpdater = new EffectiveCanvasUpdater(canvas);
    this.lineModel = lineModel;
    this.grid = grid;

    final Function2D function = lineModel.getFunction();
    if (function instanceof RealtimeFunction) {
      ((RealtimeFunction)function).setCanvasUpdater(this.canvasUpdater);
      this.isRealtime = true;
    }
    this.plotter = lineModel.getPlotter();
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
    updaterWatcher.activate(this.canvasUpdater);

    final Color oldColor = g.getColor();
    final float oldLineWidth = g.getLineWidth();
    final LineType oldLineType = g.getLineType();

    g.setColor(this.lineModel.getLineColor());
    g.setLineWidth(this.lineModel.getLineWidth());
    g.setLineType(this.lineModel.getLineType());

    this.plotter.plot(g, this.grid);
    if (this.isRealtime) {
      if (((RealtimeFunction)this.lineModel.getFunction()).isComputing()) {
        final String text = "Now computing..."; //$NON-NLS-1$
        g.drawString(text, 0, getHeight() - g.getTextHeight() + g.getTextAscent());
      }
    }

    g.setLineType(oldLineType);
    g.setLineWidth(oldLineWidth);
    g.setColor(oldColor);
  }

}
