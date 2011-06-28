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
import org.mklab.ishikura.graph.g2d.CoordinateSpaceFigure;
import org.mklab.ishikura.graph.g2d.FunctionFigure;
import org.mklab.ishikura.graph.g2d.GraphFigure;
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
    graph.setTitle("サンプルグラフ");
    graph.setNameOfX("X軸の名前");
    graph.setNameOfY("Y軸の名前");
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
    final CoordinateSpaceFigure coords = graph.getCoordinateSpace();
    final FunctionFigure function1 = coords.newFunctionFigure();
    function1.setFunction(createQuadraticFunction());
    function1.setLineWidth(3);
    function1.setLineName("y = x^2");
    final FunctionFigure function2 = coords.newFunctionFigure();
    function2.setFunction(createCubicFunction());
    function2.setLineColor(Color.GREEN);
    function2.setLineWidth(3);
    function2.setLineName("y = x^3");

    graph.setSize(400, 300);
    graph.setScope(-25, 25, -300, 300);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.add(graphComponent);
    frame.pack();
    frame.setVisible(true);
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
