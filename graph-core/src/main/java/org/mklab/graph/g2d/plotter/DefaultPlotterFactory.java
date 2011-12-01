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

import org.mklab.graph.function.ContinuousFunction2D;
import org.mklab.graph.function.DiscreteFunction2D;
import org.mklab.graph.function.Function2D;
import org.mklab.graph.function.ParameterFunction2D;
import org.mklab.graph.function.PolarFunction2D;


/**
 * デフォルトの{@link PlotterFactory}です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
public class DefaultPlotterFactory implements PlotterFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Plotter create(Function2D function) {
    if (function instanceof DiscreteFunction2D) return new DiscreteFunctionPlotter((DiscreteFunction2D)function);
    if (function instanceof ContinuousFunction2D) return new ContinuousFunctionPlotter((ContinuousFunction2D)function);
    if (function instanceof PolarFunction2D) return new PolarFunctionPlotter((PolarFunction2D)function);
    if (function instanceof ParameterFunction2D) return new ParameterFunctionPlotter((ParameterFunction2D)function);

    throw new UnsupportedOperationException();
  }

}
