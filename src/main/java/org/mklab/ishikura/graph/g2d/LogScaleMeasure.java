/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
class LogScaleMeasure extends AbstractMeasure {

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

  private double log10(double d) {
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

}
