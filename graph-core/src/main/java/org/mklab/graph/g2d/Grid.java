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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 単系列のグリッドを表すクラスです。
 * <p>
 * このクラスでは、グラフの目盛りを保持します。 目盛りの値は、モデルの座標であり、ビュー上での座標ではありません。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class Grid implements Iterable<Double> {

  private List<Double> graduations;

  /**
   * 新しく生成された<code>CoordinateSpace2D.Grid</code>オブジェクトを初期化します。
   */
  Grid() {
    this.graduations = new ArrayList<Double>();
  }

  /**
   * 目盛を追加します。
   * 
   * @param graduation 目盛
   */
  void add(double graduation) {
    this.graduations.add(Double.valueOf(graduation));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<Double> iterator() {
    return this.graduations.iterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Grid " + this.graduations; //$NON-NLS-1$
  }
}