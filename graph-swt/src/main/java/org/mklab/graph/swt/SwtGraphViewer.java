package org.mklab.graph.swt;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.mklab.graph.GraphViewer;
import org.mklab.graph.g2d.GraphFigure;


/**
 * SWTによる{@link GraphViewer}の実装です。
 * 
 * @author ishikura
 * @version $Revision$, 2011/07/02
 */
public class SwtGraphViewer implements GraphViewer {

  /**
   * {@inheritDoc}
   */
  @Override
  public void show(GraphFigure graph) {
    final Display display = Display.getDefault();
    final Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());

    final GraphCanvas canvas = new GraphCanvas(shell);
    graph.setCanvas(canvas);
    canvas.setGraphFigure(graph);

    shell.setSize(graph.getWidth(), graph.getHeight());
    shell.open();
    while (shell.isDisposed() == false) {
      while (display.readAndDispatch() == false) {
        display.sleep();
      }
    }
  }

}
