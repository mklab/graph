/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.AbstractFigure;
import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.figure.Figure;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;
import org.mklab.ishikura.graph.graphics.LineType;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
class FunctionInfoBoxFigure extends ContainerFigureImpl {

  /**
   * 与えられた関数の図を元に、線情報を追加します。
   * 
   * @param functionFigure 関数図
   */
  void addLineInfo(FunctionFigure functionFigure) {
    add(new LineInfoFigure(functionFigure));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void layout(Graphics g) {
    validateChildren(g);
    int maximumWidth = 0;
    int y = 0;
    for (Figure figure : getChildren()) {
      if (maximumWidth < figure.getWidth()) maximumWidth = figure.getWidth();
      figure.setX(0);
      figure.setY(y);

      y += figure.getHeight();
    }
    setSize(maximumWidth, y);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    Color oldColor = g.getColor();

    float oldAlpha = g.getAlpha();
    g.setAlpha(0.8f);
    g.setColor(ColorConstants.FUNCTION_INFO_BACKGROUND);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setAlpha(oldAlpha);

    g.setColor(ColorConstants.FUNCTION_INFO_BORDER);
    // 上端、右端は、親の図とわざとかぶらせる
    g.drawRect(0, -1, getWidth(), getHeight() - 1);

    g.setColor(oldColor);

    super.handleDraw(g);
  }

  static class LineInfoFigure extends AbstractFigure {

    private static final int SAMPLE_LINE_LENGTH = 30;
    private static final int BORDER_PADDING = 3;
    private static final int PADDING = 10;
    private FunctionFigure function;

    /**
     * {@link FunctionInfoBoxFigure}オブジェクトを構築します。
     * 
     * @param function 関数
     */
    LineInfoFigure(FunctionFigure function) {
      if (function == null) throw new NullPointerException();
      this.function = function;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layout(Graphics g) {
      final int width = BORDER_PADDING + SAMPLE_LINE_LENGTH + PADDING + g.computeTextWidth(this.function.getLineName()) + BORDER_PADDING;
      final int height = BORDER_PADDING + g.getTextHeight() + BORDER_PADDING;
      setSize(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDraw(Graphics g) {
      final Color oldColor = g.getColor();
      final LineType oldType = g.getLineType();
      final float oldLineWidth = g.getLineWidth();

      g.setColor(this.function.getLineColor());
      g.setLineType(this.function.getLineType());
      g.setLineWidth(this.function.getLineWidth());
      g.drawLine(BORDER_PADDING, getHeight() / 2, BORDER_PADDING + SAMPLE_LINE_LENGTH, getHeight() / 2);
      g.setLineWidth(oldLineWidth);
      g.setColor(oldColor);
      g.setLineType(oldType);

      g.drawString(this.function.getLineName(), BORDER_PADDING + SAMPLE_LINE_LENGTH + PADDING, g.getTextAscent() + BORDER_PADDING);
    }
  }
}
