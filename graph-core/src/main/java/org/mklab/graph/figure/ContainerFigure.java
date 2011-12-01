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
package org.mklab.graph.figure;

import java.util.List;


/**
 * 複数の図のコンテナを表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public interface ContainerFigure extends Figure {

  /**
   * 図の追加を行います。
   * 
   * @param figure 図
   */
  void add(Figure figure);

  /**
   * この図のサイズを、属しているすべての子のサイズに合わせます。
   */
  void pack();

  /**
   * 与えられた座標に存在する図を取得します。
   * 
   * @param x x座標
   * @param y y座標
   * @return 図
   */
  List<Figure> getFiguresAt(int x, int y);

}