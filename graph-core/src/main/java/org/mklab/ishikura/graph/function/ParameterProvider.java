/**
 * 
 */
package org.mklab.ishikura.graph.function;

/**
 * 関数に与えるパラメータを提供するインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public interface ParameterProvider {

  /**
   * パラメータの提供を行います。
   * 
   * @return パラメータ
   */
  Iterable<Double> provide();

}
