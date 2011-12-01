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
import org.mklab.graph.function.ContinuousFunction2D;
import org.mklab.graph.function.Function2D;
import org.mklab.graph.g2d.model.GraphModel;
import org.mklab.graph.g2d.model.LineModel;


/**
 * @author ishikura
 * @version $Revision$, 2011/07/02
 */
public class MultiLineSample extends GraphFigureSample {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphModel(GraphModel graphModel) {
    graphModel.setTitle("Multi Line Sample"); //$NON-NLS-1$
    graphModel.setXAxisName("x"); //$NON-NLS-1$
    graphModel.setYAxisName("y"); //$NON-NLS-1$
    final LineModel lineModel1 = new LineModel("y = x^2", Color.RED, createQuadraticFunction()); //$NON-NLS-1$
    lineModel1.setLineType(LineType.DOT);
    graphModel.addLineModel(lineModel1);
    final LineModel lineModel2 = new LineModel("y = x^3", Color.GREEN, createCubicFunction()); //$NON-NLS-1$
    graphModel.addLineModel(lineModel2);
  }

  private static Function2D createQuadraticFunction() {
    final Function2D function = new ContinuousFunction2D() {

      @Override
      public double evalY(double x) {
        return Math.pow(x, 2);
      }
    };
    return function;
  }

  private static Function2D createCubicFunction() {
    final Function2D function = new ContinuousFunction2D() {

      @Override
      public double evalY(double x) {
        return Math.pow(x, 3);
      }
    };
    return function;
  }

  /**
   * メインメソッドです。
   * 
   * @param args プログラム引数
   */
  public static void main(String[] args) {
    new MultiLineSample().launch();
  }

}
