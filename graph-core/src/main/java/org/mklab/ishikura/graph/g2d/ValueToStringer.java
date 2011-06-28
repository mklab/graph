/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * グラフ上の値を文字列で表現するインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/26
 */
public interface ValueToStringer {

  /**
   * グラフ上の値を文字列で表現します。
   * 
   * @param value グラフ上の値
   * @return 値を表現した文字列
   */
  String valueToString(double value);

}
