/**
 * 
 */
package org.mklab.graph.function;

import java.util.Iterator;


/**
 * 媒介変数表示の関数を表す抽象クラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public abstract class ParameterFunction2D implements Function2D {

  /**
   * x座標の計算を行います。
   * 
   * @param t 媒介変数
   * @return x座標
   */
  public abstract double evalX(double t);

  /**
   * y座標の計算を行います。
   * 
   * @param t 媒介変数
   * @return y座標
   */
  public abstract double evalY(double t);

  /**
   * 媒介変数を提供します。
   * 
   * @return 媒介変数
   */
  public abstract Iterator<Double> provideParameter();

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(double x, double y, double delta) {
    final Iterator<Double> parameters = provideParameter();
    while (parameters.hasNext()) {
      final double t = parameters.next().doubleValue();
      final double xx = evalX(t);
      final double yy = evalY(t);
      if (Math.abs(xx - x) <= delta && Math.abs(yy - y) <= delta) return true;
    }
    return false;
  }

}
