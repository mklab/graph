/**
 * 
 */
package org.mklab.ishikura.graph.function;

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
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(double x, double y, double delta) {
    final double r = Math.hypot(x, y);
    final double theta = Math.atan2(y, x);

    return Math.abs(evalR(theta) - r) <= delta;
  }

}
