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
public class ParameterFunctionSample extends GraphFigureSample {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphModel(GraphModel model) {
    model.setTitle("Parameter Function Sample"); //$NON-NLS-1$
    final LineModel lineModel = new LineModel("hypocycloid", Color.RED, HYPOCYCLOID); //$NON-NLS-1$
    lineModel.setLineType(LineType.DOT);
    model.addLineModel(lineModel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphFigure(GraphFigure figure) {
    super.initGraphFigure(figure);
    figure.setScope(-3, 3.5, -3, 3);
  }

  /**
   * メインメソッドです。
   * 
   * @param args プログラム引数
   */
  public static void main(String[] args) {
    new ParameterFunctionSample().launch();
  }

}
