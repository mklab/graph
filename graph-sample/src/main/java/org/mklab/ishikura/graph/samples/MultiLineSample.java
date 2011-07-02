package org.mklab.ishikura.graph.samples;

import org.mklab.ishikura.graph.function.ContinuousFunction2D;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.g2d.model.GraphModel;
import org.mklab.ishikura.graph.g2d.model.LineModel;
import org.mklab.ishikura.graph.graphics.Color;


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
