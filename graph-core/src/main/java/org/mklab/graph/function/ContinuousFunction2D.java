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

/**
 * 一つのx値に対し一つのy値を持つ関数(<code>y = f(x)</code>)を表す抽象クラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public abstract class ContinuousFunction2D implements Function2D {

  /**
   * xを与えた時の関数の値yを計算します。
   * 
   * @param x xの値
   * @return y xにおけるyの値
   */
  public abstract double evalY(double x);

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(double x, double y, double delta) {
    return Math.abs(evalY(x) - y) <= delta;
  }

}
