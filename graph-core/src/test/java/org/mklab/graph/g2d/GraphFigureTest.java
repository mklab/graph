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
