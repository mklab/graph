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
package org.mklab.graph.samples;

import org.mklab.abgr.Color;
import org.mklab.abgr.LineType;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.model.GraphModel;
import org.mklab.graph.g2d.model.LineModel;


/**
 * @author ishikura
 * @version $Revision$, 2011/07/02
 */
public class PolarFunctionSample extends GraphFigureSample {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphModel(GraphModel model) {
    model.setTitle("Polar Function Sample"); //$NON-NLS-1$
    final LineModel lineModel = new LineModel("Logarithmic Spiral", Color.RED, LOGARITHMIC_SPIRAL); //$NON-NLS-1$
    lineModel.setLineType(LineType.DOT);
    model.addLineModel(lineModel); 
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphFigure(GraphFigure figure) {
    super.initGraphFigure(figure);
    figure.setScope(-100000, 100000, -100000, 100000);
  }

  /**
   * メインメソッドです。
   * 
   * @param args プログラム引数
   */
  public static void main(String[] args) {
    new PolarFunctionSample().launch();
  }
}
