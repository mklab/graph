package org.mklab.graph.android;

import org.mklab.graph.g2d.HasCoordinateSpace;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


/**
 * @author ishikura
 * @version $Revision$, 2011/06/29
 */
class GraphViewTouchListener implements OnTouchListener {

  double lastMoveTime;
  double lastDistance;
  double velocity;
  double direction;
  boolean touching = false;
  public static final int MOMENTUM_SCROLL_REPAINT_RATE = 30;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean onTouch(View v, MotionEvent event) {
    this.touching = true;
    final GraphView graphView = (GraphView)v;
    final HasCoordinateSpace graph = graphView.getGraphFigure();
    switch (event.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_MOVE:
        // move scope
        if (event.getHistorySize() > 0) {
          final float dx = event.getHistoricalX(0) - event.getX();
          final float dy = event.getHistoricalY(0) - event.getY();
          graph.moveScope((int)dx, -(int)dy);
          graphView.invalidate();
          this.velocity = Math.hypot(dx, dy) / (event.getEventTime() - this.lastMoveTime) * MOMENTUM_SCROLL_REPAINT_RATE;
          this.direction = Math.atan2(-dy, dx);
        }
        // pinch to scale
        if (event.getPointerCount() == 2) {
          final double distance = Math.hypot(event.getX(0) - event.getX(1), event.getY(0) - event.getY(1));
          final double ratio = distance / this.lastDistance;
          if (this.lastDistance != 0) {
            final int midX = (int)((event.getX() + event.getX(1)) / 2);
            final int midY = (int)((event.getY() + event.getY(1)) / 2);
            graph.scaleScope(midX, midY, ratio);
            graphView.invalidate();
          }
          this.lastDistance = distance;
        }

        this.lastMoveTime = event.getEventTime();
        break;
      case MotionEvent.ACTION_UP:
        this.lastDistance = 0;
        this.touching = false;
        momentumScroll(graphView);
        break;
    }
    return true;
  }

  void momentumScroll(final GraphView graphView) {
    if (this.velocity < 1e-2 || this.touching) return;
    final double dx = this.velocity * Math.cos(this.direction);
    final double dy = this.velocity * Math.sin(this.direction);
    final HasCoordinateSpace graph = graphView.getGraphFigure();
    graph.moveScope((int)dx, (int)dy);
    this.velocity *= 0.95;

    graphView.postDelayed(new Runnable() {

      @Override
      public void run() {
        momentumScroll(graphView);
        graphView.invalidate();
      }
    }, MOMENTUM_SCROLL_REPAINT_RATE);
  }

}
