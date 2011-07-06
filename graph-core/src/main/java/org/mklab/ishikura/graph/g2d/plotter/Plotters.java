/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

/**
 * {@link Plotter}の実装を補助するユーティリティクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/06
 */
final class Plotters {

  private Plotters() {
    // do nothing
  }

  public static boolean isValidNumber(double d) {
    return Double.isInfinite(d) == false && Double.isNaN(d) == false;
  }

}
