/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * 軸上の値から、ビュー上の値に変換を行うクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
interface Measure {

  /**
   * ビュー上の表示範囲を設定します。
   * 
   * @param bound 表示範囲
   */
  void setBound(Bound bound);

  /**
   * viewSizeを設定します。
   * 
   * @param viewSize viewSize
   */
  void setViewSize(int viewSize);

  /**
   * viewSizeを取得します。
   * 
   * @return viewSize
   */
  int getViewSize();

  /**
   * ビューとモデルの比率を計算します。
   * 
   * @return ビューとモデルの比率
   */
  double getViewToModelRatio();

  /**
   * 数学的な座標からビュー上の座標に変換します。
   * <p>
   * ビューの範囲外でも計算します。
   * 
   * @param modelValue 数学的な座標
   * @return ビュー上の座標
   */
  int modelToViewIgnoreBound(double modelValue);

  /**
   * 数学的な座標からビュー上の座標に変換します。
   * <p>
   * ビューの範囲外ならば-1を返します。
   * 
   * @param modelValue 数学的な座標
   * @return ビュー上の座標。範囲外ならば-1
   */
  int modelToView(double modelValue);

  /**
   * ビュー上の座標から数学上の座標に変換します。
   * 
   * @param viewValue ビュー上の座標
   * @return 数学的な座標
   */
  double viewToModel(int viewValue);

  /**
   * 与えられた値が含まれているかどうか判定します。
   * 
   * @param viewValue ビュー上の座標
   * @return 含まれるかどうか
   */
  boolean containsInView(int viewValue);

}