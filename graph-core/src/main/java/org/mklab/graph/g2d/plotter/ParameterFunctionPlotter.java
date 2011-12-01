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

import java.util.Iterator;

import org.mklab.abgr.Graphics;
import org.mklab.graph.function.ParameterFunction2D;
import org.mklab.graph.g2d.GridFigure;


/**
 * 媒介変数表示の関数を描画するクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
class ParameterFunctionPlotter implements Plotter {

  ParameterFunction2D function;

  /**
   * {@link ParameterFunctionPlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  ParameterFunctionPlotter(ParameterFunction2D function) {
    if (function == null) throw new NullPointerException();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    final Iterator<Double> tSeries = this.function.provideParameter();
    final PolylineRenderer polyRenderer = new PolylineRenderer(grid);

    while (tSeries.hasNext()) {
      final double t = tSeries.next().doubleValue();
      final double modelX = this.function.evalX(t);
      final double modelY = this.function.evalY(t);
      if (Plotters.isValidNumber(modelX) == false || Plotters.isValidNumber(modelY) == false) continue;

      final int viewX = grid.modelToViewXIgnoreBound(modelX);
      final int viewY = grid.modelToViewYIgnoreBound(modelY);
      polyRenderer.appendPoint(viewX, viewY);
    }
    polyRenderer.draw(g);
  }

}
