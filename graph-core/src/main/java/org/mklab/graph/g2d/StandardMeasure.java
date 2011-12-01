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
 * 一般的なグラフの{@link Measure}実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/13
 */
final class StandardMeasure extends AbstractMeasure {

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModel(int viewValue) {
    return this.bound.getStart() + getViewToModelRatio() * viewValue;
  }

  private double getViewToModelRatio() {
    return this.bound.getWidth() / (this.viewSize - 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int modelToViewIgnoreBound(double modelValue) {
    return modelToView(modelValue, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int modelToView(double modelValue) {
    return modelToView(modelValue, false);
  }

  private int modelToView(double modelValue, boolean ignoreBound) {
    if (this.bound == null) throw new IllegalStateException("bound not set!"); //$NON-NLS-1$

    double scopeStart = this.bound.getStart();
    double scopeEnd = this.bound.getEnd();
    if (ignoreBound == false) {
      if (modelValue < scopeStart || modelValue >= scopeEnd) return -1;
    }

    final double modelSize = scopeEnd - scopeStart;
    final double viewValue = (modelValue - scopeStart) * this.viewSize / modelSize;
    final int viewValueInt = (int)(viewValue > 0 ? (viewValue + 0.5) : viewValue - 0.5);

    if (ignoreBound == false) {
      if (viewValueInt == this.viewSize && viewValue < this.viewSize) return viewValueInt - 1;
    }
    return viewValueInt;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Bound fixBound(@SuppressWarnings("hiding") Bound bound) {
    return bound;
  }
}
