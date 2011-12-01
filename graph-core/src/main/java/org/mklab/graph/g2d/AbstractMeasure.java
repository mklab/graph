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