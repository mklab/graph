package org.mklab.ishikura.graph.android;

import org.mklab.ishikura.graph.g2d.GraphFigure;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


/**
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
class GraphViewTouchListener implements OnTouchListener {

  double lastDistance;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    final GraphView graphView = (GraphView)v;
    final GraphFigure graph = graphView.getGraphFigure();
    switch (event.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_MOVE:
        // move scope
        if (event.getHistorySize() > 0) {
          final float dx = event.getHistoricalX(0) - event.getX();
          final float dy = event.getHistoricalY(0) - event.getY();
          graph.move((int)dx, -(int)dy);
          graphView.invalidate();
        }
        // pinch to scale
        if (event.getPointerCount() == 2) {
          final double distance = Math.hypot(event.getX(0) - event.getX(1), event.getY(0) - event.getY(1));
          final double ratio = distance / this.lastDistance;
          if (this.lastDistance != 0) {
            final int midX = (int)((event.getX() + event.getX(1)) / 2);
            final int midY = (int)((event.getY() + event.getY(1)) / 2);
            graph.scale(midX, midY, ratio);
            graphView.invalidate();
          }
          this.lastDistance = distance;
        }
        break;
      case MotionEvent.ACTION_POINTER_UP:
        this.lastDistance = 0;
    }
    return true;
  }

}
