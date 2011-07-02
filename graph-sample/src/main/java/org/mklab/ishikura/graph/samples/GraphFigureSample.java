package org.mklab.ishikura.graph.samples;

import org.mklab.ishikura.graph.GraphViewer;
import org.mklab.ishikura.graph.g2d.GraphFigure;
import org.mklab.ishikura.graph.g2d.model.GraphModel;
import org.mklab.ishikura.graph.swing.SwingGraphViewer;


/**
 * {@link GraphFigure}のサンプルの基底クラスです。
 * 
 * @author ishikura
 * @version $Revision$, 2011/07/02
 */
public abstract class GraphFigureSample implements SampleFunctions {

  /**
   * サンプルを起動します。
   */
  public void launch() {
    final GraphFigure figure = new GraphFigure();
    initGraphModel(figure.getModel());
    initGraphFigure(figure);

    final GraphViewer viewer = createGraphViewer();
    viewer.show(figure);
  }

  /**
   * グラフ図を生成します。
   * 
   * @param figure 図
   */
  protected abstract void initGraphModel(GraphModel model);

  protected void initGraphFigure(final GraphFigure figure) {
    figure.setScope(0, 100, 0, 100);
    figure.setSize(500, 500);
  }

  private final GraphViewer createGraphViewer() {
    return new SwingGraphViewer();
  }

}
