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
import org.mklab.graph.figure.AbstractFigure;


/**
 * ガイドの表示を行う図を表すクラスです。
 * <p>
 * ガイドとは、グラフ上をマウスが動くとそれに応じてそのx座標とy座標を線で知らせてくれるあれです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 */
public class GuideFigure extends AbstractFigure {

  /** ガイドの線色です。 */
  private Color lineColor = ColorConstants.GUIDE_LINE;
  /** グリッド部分の図です。座標変換に利用します。 */
  private GridFigure grid;
  /** ガイドのx座標(モデル上)です。 */
  private double guideX;
  /** ガイドのy座標(モデル上)です。 */
  private double guideY;

  /**
   * {@link GuideFigure}オブジェクトを構築します。
   * 
   * @param grid グリッド
   */
  public GuideFigure(GridFigure grid) {
    if (grid == null) throw new NullPointerException();
    this.grid = grid;
  }

  /**
   * ガイドのx座標を設定します。
   * 
   * @param guideX ガイドのx座標
   */
  public void setGuideX(double guideX) {
    this.guideX = guideX;
  }

  /**
   * ガイドのy座標を設定します。
   * 
   * @param guideY ガイドのy座標
   */
  public void setGuideY(double guideY) {
    this.guideY = guideY;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    final int x = this.grid.modelToViewX(this.guideX);
    final int y = this.grid.modelToViewY(this.guideY);

    g.setColor(this.lineColor);
    g.drawLine(x, 0, x, getHeight() - 1);
    g.drawLine(0, y, getWidth() - 1, y);
  }

}
