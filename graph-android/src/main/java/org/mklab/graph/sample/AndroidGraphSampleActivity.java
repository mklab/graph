package org.mklab.graph.sample;

import org.mklab.abgr.Color;
import org.mklab.graph.android.GraphView;
import org.mklab.graph.function.ContinuousFunction2D;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.model.GraphModel;
import org.mklab.graph.g2d.model.LineModel;

import android.app.Activity;
import android.os.Bundle;


/**
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
public class AndroidGraphSampleActivity extends Activity {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final GraphView graphView = new GraphView(getApplicationContext());
    final GraphFigure graph = new GraphFigure();
    final GraphModel model = graph.getModel();
    model.setXAxisName("X軸"); //$NON-NLS-1$
    model.setYAxisName("Y軸"); //$NON-NLS-1$
    model.setTitle("Sample Graph"); //$NON-NLS-1$
    graph.setScope(-5, 5, -2, 2);

    final LineModel lineModel = new LineModel("y = sin(x)", Color.RED, new ContinuousFunction2D() { //$NON-NLS-1$

          @Override
          public double evalY(double x) {
            return Math.sin(x);
          }

        });
    model.addLineModel(lineModel);

    graphView.setGraphFigure(graph);
    setContentView(graphView);
  }
}
