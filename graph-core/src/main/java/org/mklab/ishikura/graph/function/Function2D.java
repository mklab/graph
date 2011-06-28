/*
 * Created on 2010/10/12
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.function;

/**
 * 一つのx値に対し一つのy値を持つ関数を表す抽象クラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/12
 */
public abstract class Function2D implements Function<Double, Double> {

  /**
   * @see org.mklab.ishikura.graph.function.Function#eval(java.lang.Object,
   *      org.mklab.ishikura.graph.function.ReturnmentContainer)
   */
  @Override
  public void eval(Double arguments, ReturnmentContainer<Double> returnment) {
    returnment.set(Double.valueOf(evalY(arguments.doubleValue())));
  }

  /**
   * xを与えた時の関数の値yを計算します。
   * 
   * @param x xの値
   * @return y xにおけるyの値
   */
  public abstract double evalY(double x);

}
