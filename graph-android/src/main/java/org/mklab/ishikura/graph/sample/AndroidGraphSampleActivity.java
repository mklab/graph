package org.mklab.ishikura.graph.sample;

import org.mklab.ishikura.graph.android.GraphView;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.g2d.FunctionFigure;
import org.mklab.ishikura.graph.g2d.GraphFigure;

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
    graph.setNameOfX("X軸");
    graph.setNameOfY("Y軸");
    graph.setSize(600, 600);
    graph.setScope(0, 100, 0, 100);
    final FunctionFigure function = graph.getCoordinateSpace().newFunctionFigure();
    function.setFunction(new Function2D() {

      @Override
      public double evalY(double x) {
        return x;
      }

    });
    graphView.setGraphFigure(graph);
    setContentView(graphView);
  }

}
