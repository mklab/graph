package org.mklab.ishikura.graph.sample;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.g2d.FunctionFigure;
import org.mklab.ishikura.graph.g2d.GraphFigure;
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
    final FunctionFigure function = graph.getCoordinateSpace().newFunctionFigure();
    function.setFunction(new Function2D() {

      @Override
      public double evalY(double x) {
        return 2 * x;
      }
    });
    graph.setSize(300, 300);
    graph.setScope(0, 100, 0, 100);
    graph.setTitle("Hello Graph"); //$NON-NLS-1$
    graph.setNameOfX("X");
    graph.setNameOfY("Y");
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
