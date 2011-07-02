/**
 * 
 */
package org.mklab.ishikura.graph.function;

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
