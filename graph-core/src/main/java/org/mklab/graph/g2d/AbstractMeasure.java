/**
 * 
 */
package org.mklab.graph.g2d;

/**
 * {@link Measure}の抽象実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
abstract class AbstractMeasure implements Measure {

  protected Bound bound;
  protected int viewSize;

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setBound(Bound bound) {
    checkBound(bound);
    this.bound = bound;
  }

  protected void checkBound(@SuppressWarnings({"unused", "hiding"}) Bound bound) {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setViewSize(int viewSize) {
    if (viewSize < 0) throw new IllegalArgumentException();
    this.viewSize = viewSize;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getViewSize() {
    return this.viewSize;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsInView(int viewValue) {
    return viewValue < this.viewSize && viewValue >= 0;
  }

}