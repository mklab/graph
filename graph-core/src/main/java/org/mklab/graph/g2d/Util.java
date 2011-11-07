/**
 * 
 */
package org.mklab.graph.g2d;

import org.mklab.graph.figure.Figure;
import org.mklab.graph.figure.Figures;
import org.mklab.graph.figure.Point;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/03
 */
class Util {

  /**
   * <code>from</code>上の座標<code>xOnFrom</code>をモデル上の座標に変換します。
   * 
   * @param <E> 図の型
   * @param from 座標<code>xOnFrom</code>を測定した図形
   * @param to モデル上の座標にする図形
   * @param xOnFrom 座標
   * @return x座標
   */
  static <E extends Figure & HasCoordinateSpace> double viewToModelX(E from, E to, int xOnFrom) {
    final Point pointOnTo = Figures.convertPoint(from, to, new Point(xOnFrom, 0));
    return to.viewToModelX(pointOnTo.x);
  }

  /**
   * <code>from</code>上の座標<code>yOnFrom</code>をモデル上の座標に変換します。
   * 
   * @param <E> 図の型
   * @param from 座標<code>yOnFrom</code>を測定した図形
   * @param to モデル上の座標にする図形
   * @param yOnFrom 座標
   * @return y座標
   */
  static <E extends Figure & HasCoordinateSpace> double viewToModelY(E from, E to, int yOnFrom) {
    final Point pointOnTo = Figures.convertPoint(from, to, new Point(0, yOnFrom));
    return to.viewToModelY(pointOnTo.y);
  }

  /**
   * <code>from</code>上の座標をモデル上の座標に変換します。
   * 
   * @param <E> 図の型
   * @param from 座標を測定した図形
   * @param to モデル上の座標にする図形
   * @param xOnFrom x座標
   * @param yOnFrom y座標
   * @param ratio 拡大率
   */
  static <E extends Figure & HasCoordinateSpace> void scaleScope(E from, E to, int xOnFrom, int yOnFrom, double ratio) {
    final Point pointOnTo = Figures.convertPoint(from, to, new Point(xOnFrom, yOnFrom));
    to.scaleScope(pointOnTo.x, pointOnTo.y, ratio);
  }

}
