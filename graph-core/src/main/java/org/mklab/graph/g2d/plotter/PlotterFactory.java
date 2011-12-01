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

import org.mklab.graph.function.Function2D;


/**
 * {@link Plotter}インスタンスを生成するファクトリーです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
public interface PlotterFactory {

  /**
   * 与えられた関数を描画する{@link Plotter}を生成します。
   * 
   * @param function 描画する関数
   * @return {@link Plotter}インスタンス
   */
  Plotter create(Function2D function);

}
