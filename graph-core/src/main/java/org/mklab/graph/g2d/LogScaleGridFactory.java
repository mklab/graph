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

/**
 * ログスケールのグラフのグリッドを生成する{@link GridFactory}です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public class LogScaleGridFactory implements GridFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Grid create(double start, double end) {
    if (start > end) throw new IllegalArgumentException();
    if (start <= 0) throw new IllegalArgumentException("Log scale not support zero or negative value."); //$NON-NLS-1$

    final double logStart = Math.log10(start);
    int n = (int)Math.ceil(logStart);
    Grid grid = new Grid();
    if (Math.pow(10, n) > start) n--;
    while (true) {
      final double g = Math.pow(10, n);
      grid.add(g);
      n++;
      if (g >= end) break;
    }
    return grid;
  }

}
