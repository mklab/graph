package org.mklab.graph.samples;

import org.mklab.graph.GraphViewer;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.model.GraphModel;
import org.mklab.graph.swing.SwingGraphViewer;


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
   * @param model 図
   */
  protected abstract void initGraphModel(GraphModel model);

  /**
   * グラフを初期化します。
   * 
   * @param figure グラフ
   */
  protected void initGraphFigure(final GraphFigure figure) {
    figure.setScope(0, 100, 0, 100);
    figure.setSize(500, 500);
  }

  private final static GraphViewer createGraphViewer() {
    return new SwingGraphViewer();
  }

}
