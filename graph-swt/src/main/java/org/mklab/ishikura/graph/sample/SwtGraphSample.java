package org.mklab.ishikura.graph.sample;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.g2d.FunctionFigure;
import org.mklab.ishikura.graph.g2d.GraphFigure;
import org.mklab.ishikura.graph.g2d.model.GraphModel;
import org.mklab.ishikura.graph.g2d.model.LineModel;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.swt.GraphCanvas;


/**
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
@SuppressWarnings("all")
public class SwtGraphSample {

  public static void main(String[] args) {
    final Display display = new Display();
    final Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    final GraphCanvas canvas = new GraphCanvas(shell);

    final GraphFigure graph = new GraphFigure();
    final GraphModel model = graph.getModel();
    final LineModel lineModel = new LineModel("y = 2x", Color.RED, new Function2D() {

      @Override
      public double evalY(double x) {
        return 2 * x;
      }
    });
    model.addLineModel(lineModel);
    model.setTitle("Hello Graph"); //$NON-NLS-1$
    model.setXAxisName("X");
    model.setYAxisName("Y");

    graph.setSize(300, 300);
    graph.setScope(0, 100, 0, 100);
    canvas.setGraphFigure(graph);

    shell.setSize(500, 500);
    shell.open();
    while (shell.isDisposed() == false) {
      while (display.readAndDispatch() == false) {
        display.sleep();
      }
    }
  }

}
