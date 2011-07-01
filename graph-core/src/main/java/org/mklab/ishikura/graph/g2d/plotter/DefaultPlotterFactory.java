/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import org.mklab.ishikura.graph.function.DiscreteFunction2D;
import org.mklab.ishikura.graph.function.Function2D;


/**
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

    return new ContinuousFunctionPlotter(function);
  }

}
