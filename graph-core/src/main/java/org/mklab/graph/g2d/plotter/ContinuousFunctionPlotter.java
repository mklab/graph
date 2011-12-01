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
package org.mklab.graph.g2d.plotter;

import org.mklab.abgr.Graphics;
import org.mklab.graph.function.ContinuousFunction2D;
import org.mklab.graph.g2d.GridFigure;


/**
 * 連続時間の関数を描画するクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
class ContinuousFunctionPlotter implements Plotter {

  private ContinuousFunction2D function;

  /**
   * {@link ContinuousFunctionPlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  ContinuousFunctionPlotter(ContinuousFunction2D function) {
    super();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    final int width = grid.getWidth();
    final PolylineRenderer r = new PolylineRenderer(grid);
    for (int viewX = 0; viewX < width; viewX++) {
      final double modelX = grid.viewToModelX(viewX);
      final double modelY = this.function.evalY(modelX);
      if (Plotters.isValidNumber(modelY) == false) continue;

      final int viewY = grid.modelToViewYIgnoreBound(modelY);
      r.appendPoint(viewX, viewY);
    }
    r.draw(g);
  }

}
