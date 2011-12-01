/*
 * Copyright (C) 2011 Koga Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mklab.graph.g2d;

/**
 * ログスケールのグラフの{@link Measure}の実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
final class LogScaleMeasure extends AbstractMeasure {

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

}
