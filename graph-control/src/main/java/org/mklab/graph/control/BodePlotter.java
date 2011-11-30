/*
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
package org.mklab.graph.control;

import org.mklab.abgr.Graphics;
import org.mklab.graph.g2d.GridFigure;
import org.mklab.graph.g2d.plotter.DiscreteFunctionPlotter;


/**
 * @author Yuhi Ishikura
 */
public class BodePlotter extends DiscreteFunctionPlotter {

  /**
   * {@link BodePlotter}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  public BodePlotter(BodeFunction function) {
    super(function);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void plot(Graphics g, GridFigure grid) {
    ((BodeFunction)getFunction()).compute(grid.getScope(), grid.getWidth());
    super.plot(g, grid);
  }

}