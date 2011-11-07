package org.mklab.graph.samples;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.mklab.abgr.Color;
import org.mklab.graph.function.ContinuousFunction2D;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.model.DataModel;
import org.mklab.graph.g2d.model.LineModel;
import org.mklab.graph.jcas.JcasContinuousFunction2D;
import org.mklab.graph.swing.GraphComponent;


/**
 * @author ishikura
 * @version $Revision$, 2011/07/03
 */
public class JcasFunctionSample {

  /**
   * メインメソッドです。
   * 
   * @param args プログラム引数。このプログラムでは利用しません。
   */
  public static void main(String[] args) {
    final JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    final GraphComponent graphComponent = new GraphComponent();
    final GraphFigure graph = new GraphFigure();
    graph.setSize(500, 500);
    graph.setScope(-100, 100, -100, 100);

    graphComponent.setPreferredSize(new Dimension(graph.getWidth(), graph.getHeight()));
    graphComponent.setGraph(graph);
    frame.add(graphComponent);
    final JTextField expression = new JTextField("f(x)=x"); //$NON-NLS-1$
    frame.add(expression, BorderLayout.NORTH);
    final JButton plotButton = new JButton(new AbstractAction("Plot") { //$NON-NLS-1$

          /** for serialization. */
          private static final long serialVersionUID = 1L;

          @Override
          public void actionPerformed(@SuppressWarnings("unused") ActionEvent e) {
            final String exp = expression.getText();
            final ContinuousFunction2D f = JcasContinuousFunction2D.parse(exp);
            final DataModel dataModel = graph.getModel().getDataModel();
            if (dataModel.getLineCount() > 0) {
              dataModel.removeLineModel(0);
            }
            final LineModel lineModel = new LineModel(exp, Color.RED, f);
            dataModel.addLineModel(lineModel);
            graphComponent.repaint();
          }
        });
    frame.getRootPane().setDefaultButton(plotButton);
    frame.add(plotButton, BorderLayout.SOUTH);
    frame.pack();

    frame.setVisible(true);
  }

}
