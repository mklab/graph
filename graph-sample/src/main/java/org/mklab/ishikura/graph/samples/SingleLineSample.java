package org.mklab.ishikura.graph.samples;

import org.mklab.ishikura.graph.function.ContinuousFunction2D;
import org.mklab.ishikura.graph.g2d.model.GraphModel;
import org.mklab.ishikura.graph.g2d.model.LineModel;
import org.mklab.ishikura.graph.graphics.Color;


/**
 * @author ishikura
 * @version $Revision$, 2011/07/02
 */
public class SingleLineSample extends GraphFigureSample {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphModel(GraphModel model) {
    model.setTitle("Single Line Sample"); //$NON-NLS-1$

    final LineModel line = new LineModel("y = x", Color.RED, new ContinuousFunction2D() { //$NON-NLS-1$

          @Override
          public double evalY(double x) {
            return x;
          }
        });
    model.addLineModel(line);
  }

  /**
   * メインメソッドです。
   * 
   * @param args プログラム引数
   */
  public static void main(String[] args) {
    new SingleLineSample().launch();
  }
}
