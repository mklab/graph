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

import static org.junit.Assert.*;

import org.junit.Test;
import org.mklab.abgr.Color;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.model.GraphModel;


/**
 * {@link GraphFigure}のテストクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/06
 */
public class GraphFigureTest {

  /**
   * モデルと図のプロパティが結び付けられているかどうかのテストを行います。
   */
  @Test
  public void testModelBindings() {
    final GraphFigure figure = new GraphFigure();
    figure.setCanvas(new CanvasMock());
    final GraphModel model = figure.getModel();
    final Color colorForTest = new Color(0, 1, 2);
    final String textForTest = "hoge"; //$NON-NLS-1$

    model.setBackgroundColor(colorForTest);
    assertEquals(colorForTest, figure.getBackgroundColor());

    model.setForegroundColor(colorForTest);
    assertEquals(colorForTest, figure.getBaseGraphFigure().getXLabel().getColor());
    assertEquals(colorForTest, figure.getBaseGraphFigure().getYLabel().getColor());
    assertEquals(colorForTest, figure.getBaseGraphFigure().getTitleLabel().getColor());

    model.setGridBackgroundColor(colorForTest);
    assertEquals(colorForTest, figure.getCoordinateSpace().getGrid().getBackgroundColor());

    model.setGridBorderColor(colorForTest);
    assertEquals(colorForTest, figure.getCoordinateSpace().getBorderColor());

    model.setGridColor(colorForTest);
    assertEquals(colorForTest, figure.getCoordinateSpace().getGrid().getGridColor());

    model.setInfoBoxBackgroundColor(colorForTest);
    assertEquals(colorForTest, figure.getCoordinateSpace().getInfoBox().getBackgroundColor());

    model.setMinorGridColor(colorForTest);
    assertEquals(colorForTest, figure.getCoordinateSpace().getGrid().getMinorGridColor());

    model.setMinorGridEnabled(false);
    assertFalse(figure.getCoordinateSpace().getGrid().isMinorGridEnabled());
    model.setMinorGridEnabled(true);
    assertTrue(figure.getCoordinateSpace().getGrid().isMinorGridEnabled());

    model.setGridEnabled(false);
    assertFalse(figure.getCoordinateSpace().getGrid().isGridEnabled());
    model.setGridEnabled(true);
    assertTrue(figure.getCoordinateSpace().getGrid().isGridEnabled());

    model.setTitle(textForTest);
    assertEquals(textForTest, figure.getBaseGraphFigure().getTitleLabel().getText());

    model.setXAxisName(textForTest);
    assertEquals(textForTest, figure.getBaseGraphFigure().getXLabel().getText());

    model.setYAxisName(textForTest);
    assertEquals(textForTest, figure.getBaseGraphFigure().getYLabel().getText());
  }

}
