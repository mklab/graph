package org.mklab.graph.samples;

import org.mklab.graph.g2d.model.GraphModel;


/**
 * @author ishikura
 * @version $Revision$, 2011/07/02
 */
public class NoLineSample extends GraphFigureSample {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphModel(GraphModel model) {
    model.setTitle("No Lines Sample"); //$NON-NLS-1$
  }

  /**
   * メインメソッドです。
   * 
   * @param args プログラム引数
   */
  public static void main(String[] args) {
    new NoLineSample().launch();
  }
}
