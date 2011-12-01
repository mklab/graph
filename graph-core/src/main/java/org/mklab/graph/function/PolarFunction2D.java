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
package org.mklab.graph.function;

import java.util.Iterator;


/**
 * 極方程式(<code>r = f(theta)</code>)による関数を表すインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public abstract class PolarFunction2D implements Function2D {

  /**
   * radianを元にrを求めます。
   * 
   * @param radian 角度
   * @return r
   */
  public abstract double evalR(double radian);

  /**
   * {@link #evalR(double)}で与えるべきthetaを提供します。
   * 
   * @return theta
   */
  public Iterator<Double> provideTheta() {
    return Functions.createDoubleIterator(0, 2 * Math.PI, 1000);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(double x, double y, double delta) {
    final double r = Math.hypot(x, y);
    final double theta = Math.atan2(y, x);

    return Math.abs(evalR(theta) - r) <= delta;
  }

}
