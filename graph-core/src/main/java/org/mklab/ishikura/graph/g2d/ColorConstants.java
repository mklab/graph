/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.graphics.Color;


/**
 * グラフでデフォルトで用いる色情報を保持するインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public interface ColorConstants {

  /** グラフ全体の背景色です。 */
  Color BACKGROUND = Color.GRAY;
  /** 目盛り文字列の色です。 */
  Color GRADUATION_TEXT = Color.BLACK;
  /** 座標系のボーダーの色です。 */
  Color COORDINATES_BORDER = Color.BLACK;
  /** 座標系の背景色です。 */
  Color COORDINATES_BACKGROUND = Color.WHITE;
  /** 関数情報表示ボックスの背景色です。 */
  Color FUNCTION_INFO_BACKGROUND = Color.WHITE;
  /** 関数情報表示ボックスのボーダーの色です。 */
  Color FUNCTION_INFO_BORDER = Color.GRAY;
  /** 関数の色です。 */
  Color FUNCTION_LINE = Color.RED;
  /** 座標軸の色です。 */
  Color AXIS = Color.BLACK;
  /** グリッドの色です。 */
  Color GRID = Color.BLUE;
  /** ガイドの線の色です。 */
  Color GUIDE_LINE = Color.BLACK;

}
