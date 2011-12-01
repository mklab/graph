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
package org.mklab.graph.g2d.plotter;

import org.mklab.abgr.Graphics;
import org.mklab.graph.g2d.GridFigure;


/**
 * 関数をグラフに描画するインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
public interface Plotter {

  /**
   * 関数を描画対象<code>g</code>に描画します。
   * 
   * @param g 描画対象
   * @param grid グリッド
   */
  void plot(Graphics g, GridFigure grid);

}
