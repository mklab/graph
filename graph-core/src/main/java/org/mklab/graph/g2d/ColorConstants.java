/*
 * Copyright (C) 2011 Koga Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mklab.graph.g2d;

import org.mklab.abgr.Color;


/**
 * グラフでデフォルトで用いる色情報を保持するインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public interface ColorConstants {

  /** グラフ全体の背景色です。 */
  Color BACKGROUND = Color.GRAY;
  /** グラフのラベルなどの文字の色です。 */
  Color FOREGROUND = Color.BLACK;
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
  /** マイナーグリッドの色です。 */
  Color MINOR_GRID = new Color(192, 192, 255);
  /** ガイドの線の色です。 */
  Color GUIDE_LINE = Color.BLACK;

}
