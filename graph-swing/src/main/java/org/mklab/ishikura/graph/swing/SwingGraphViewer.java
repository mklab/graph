/**
 * 
 */
package org.mklab.ishikura.graph.swing;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.mklab.ishikura.graph.GraphViewer;
import org.mklab.ishikura.graph.g2d.GraphFigure;


/**
 * Swingによるグラフビューアの実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public class SwingGraphViewer implements GraphViewer {

  /**
   * {@inheritDoc}
   */
  @Override
  public void show(final GraphFigure graph) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          final JFrame frame = new JFrame();
          frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

          final GraphComponent graphComponent = new GraphComponent();
          graphComponent.setGraph(graph);

          frame.add(graphComponent);
          graphComponent.setPreferredSize(new Dimension(graph.getWidth(), graph.getHeight()));
          frame.pack();

          frame.setVisible(true);
        }

      });
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

}
