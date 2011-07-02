/**
 * 
 */
package org.mklab.ishikura.graph.sample;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.mklab.ishikura.graph.figure.Figure;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.g2d.FunctionFigure;
import org.mklab.ishikura.graph.g2d.GraphFigure;
import org.mklab.ishikura.graph.g2d.model.GraphModel;
import org.mklab.ishikura.graph.g2d.model.LineModel;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.swing.GraphComponent;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
@SuppressWarnings("all")
public class GraphSample {

  /**
   * メインメソッドです。
   * 
   * @param args コマンドライン引数
   */
  public static void main(final String[] args) {
    final GraphComponent graphComponent = new GraphComponent();
    graphComponent.setPreferredSize(new Dimension(500, 400));
    final GraphFigure graph = graphComponent.getGraph();
    graphComponent.addMouseListener(new MouseAdapter() {

      /**
       * {@inheritDoc}
       */
      @Override
      public void mouseClicked(MouseEvent e) {
        for (Figure fig : graph.getFiguresAt(e.getX(), e.getY())) {
          if (fig instanceof FunctionFigure) {
            System.out.println("Function clicked.");
          }
        }
      }
    });

    final GraphModel graphModel = graph.getModel();
    initializeModel(graphModel);

    graph.setSize(400, 300);
    graph.setScope(-25, 25, -300, 300);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.add(graphComponent);
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * @param graphModel
   */
  private static void initializeModel(final GraphModel graphModel) {
    graphModel.setTitle("サンプルグラフ");
    graphModel.setXAxisName("X軸の名前");
    graphModel.setYAxisName("Y軸の名前");
    final LineModel lineModel1 = new LineModel("y = x^2", Color.RED, createQuadraticFunction());
    lineModel1.setLineWidth(3);
    graphModel.addLineModel(lineModel1);
    final LineModel lineModel2 = new LineModel("y = x^3", Color.GREEN, createCubicFunction());
    lineModel2.setLineWidth(3);
    graphModel.addLineModel(lineModel2);
  }

  private static Function2D createQuadraticFunction() {
    final Function2D function = new Function2D() {

      @Override
      public double evalY(double x) {
        return Math.pow(x, 2);
      }
    };
    return function;
  }

  private static Function2D createCubicFunction() {
    final Function2D function = new Function2D() {

      @Override
      public double evalY(double x) {
        return Math.pow(x, 3);
      }
    };
    return function;
  }

}
