package org.mklab.ishikura.graph.samples;

import org.mklab.ishikura.graph.g2d.GraphFigure;
import org.mklab.ishikura.graph.g2d.model.GraphModel;
import org.mklab.ishikura.graph.g2d.model.LineModel;
import org.mklab.ishikura.graph.graphics.Color;


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
    model.addLineModel(new LineModel("Logarithmic Spiral", Color.RED, LOGARITHMIC_SPIRAL)); //$NON-NLS-1$
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
