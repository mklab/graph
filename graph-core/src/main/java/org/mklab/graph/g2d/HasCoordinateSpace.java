/**
 * 
 */
package org.mklab.graph.g2d;

/**
 * 座標空間を持つことを表すインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/03
 */
public interface HasCoordinateSpace {

  /**
   * 表示位置を移動します。
   * 
   * @param dx x方向の移動量
   * @param dy y方向の移動量
   */
  void moveScope(int dx, int dy);

  /**
   * 与えられた座標を中心として拡大を行います。
   * 
   * @param x 中心とするx座標
   * @param y 中心とするy座標
   * @param ratio 拡大率
   */
  void scaleScope(int x, int y, double ratio);

  /**
   * 表示範囲を設定します。
   * 
   * @param scope 表示範囲
   */
  void setScope(Scope scope);

  /**
   * ビューのx座標からモデルの座標へ変換します。
   * 
   * @param x x座標
   * @return モデルのx座標
   */
  double viewToModelX(int x);

  /**
   * ビューのy座標からモデルの座標へ変換します。
   * 
   * @param y y座標
   * @return モデルのy座標
   */
  double viewToModelY(int y);

}