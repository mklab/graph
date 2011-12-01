/**
 * 
 */
package org.mklab.graph.g2d;

/**
 * ログスケールのグラフの{@link Measure}の実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
final class LogScaleMeasure extends AbstractMeasure {

  private double minimumValue = 1e-10;

  /**
   * {@inheritDoc}
   */
  @Override
  protected void checkBound(@SuppressWarnings("hiding") Bound bound) {
    super.checkBound(bound);
    if (bound.getStart() <= 0) throw new IllegalArgumentException("Log scale graph not support zero or negative value."); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int modelToViewIgnoreBound(double modelValue) {
    final double logModelStart = log10(this.bound.getStart());
    final double logModelEnd = log10(this.bound.getEnd());
    final double logModelValue = log10(modelValue);

    return (int)((logModelValue - logModelStart) * this.viewSize / (logModelEnd - logModelStart));
  }

  private static double log10(double d) {
    if (d < 0) return -Math.log10(-d);
    if (d == 0) return 0;

    return Math.log10(d);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int modelToView(double modelValue) {
    if (modelValue < this.bound.getStart() || modelValue >= this.bound.getEnd()) return -1;
    return modelToViewIgnoreBound(modelValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModel(int viewValue) {
    final double logModelStart = log10(this.bound.getStart());
    final double logModelEnd = log10(this.bound.getEnd());
    double logModelValue = logModelStart + viewValue * (logModelEnd - logModelStart) / this.viewSize;

    return Math.pow(10, logModelValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Bound fixBound(@SuppressWarnings("hiding") Bound bound) {
    double start = bound.getStart();
    double end = bound.getEnd();

    boolean changed = false;
    if (end <= this.minimumValue) {
      final double d = end - start;
      start = this.minimumValue;
      end = start + d;
      changed = true;
    } else if (start <= this.minimumValue) {
      start = this.minimumValue;
      if (end == this.minimumValue) {
        end = this.minimumValue * 2;
      }
      changed = true;
    }

    return changed ? new Bound(start, end) : bound;
  }

}
