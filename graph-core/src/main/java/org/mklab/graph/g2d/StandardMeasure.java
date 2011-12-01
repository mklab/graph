/*
 * Created on 2010/10/13
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
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
