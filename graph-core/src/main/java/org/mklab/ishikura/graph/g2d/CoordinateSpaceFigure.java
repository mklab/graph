/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.ContainerFigure;


/**
 * 座標空間を表す図のインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public interface CoordinateSpaceFigure extends ContainerFigure {

  /**
   * 格子部分の図を取得します。
   * 
   * @return グリッド
   */
  GridFigure getGrid();

  /**
   * 表示位置を移動します。
   * 
   * @param dx x方向の移動量
   * @param dy y方向の移動量
   */
  void moveScope(final int dx, final int dy);

  /**
   * 与えられた座標を中心として拡大を行います。
   * 
   * @param x 中心とするx座標
   * @param y 中心とするy座標
   * @param ratio 拡大率
   */
  void scaleScope(final int x, final int y, double ratio);

  /**
   * 表示範囲を設定します。
   * 
   * @param scope 表示範囲
   */
  void setScope(Scope scope);

  /**
   * 関数の図を新たに作成します。
   * 
   * @param functionFigure 関数
   * @return 新しく追加した関数の図
   */
  FunctionFigure newFunctionFigure();

  /**
   * 関数の図を削除します。
   * 
   * @param figure 関数の図
   * @throws IllegalArgumentException 関数図がこの座標系に存在しない場合
   */
  void removeFunctionFigure(FunctionFigure figure);

}