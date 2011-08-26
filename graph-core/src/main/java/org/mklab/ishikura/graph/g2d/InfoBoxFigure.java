/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.abgr.Color;
import org.mklab.abgr.Graphics;
import org.mklab.abgr.LineType;
import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.g2d.model.DataModel;
import org.mklab.ishikura.graph.g2d.model.LineModel;


/**
 * グラフに表示している関数の情報を表示する図です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
class InfoBoxFigure extends ContainerFigureImpl {

  private static final int SAMPLE_LINE_LENGTH = 30;
  private static final int BORDER_PADDING = 3;
  private static final int ROW_PADDING = 10;

  private DataModel dataModel;

  /**
   * {@link InfoBoxFigure}オブジェクトを構築します。
   * 
   * @param dataModel 関数データモデル
   */
  InfoBoxFigure(DataModel dataModel) {
    if (dataModel == null) throw new NullPointerException();
    this.dataModel = dataModel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void layout(Graphics g) {
    int maximumWidth = 0;

    int bodyHeight = 0;
    for (LineModel lineModel : this.dataModel) {
      final int labelWidth = g.computeTextWidth(lineModel.getLabel());
      if (maximumWidth < labelWidth) maximumWidth = labelWidth;
      bodyHeight += g.getTextHeight() + ROW_PADDING;
    }

    final int width = BORDER_PADDING + SAMPLE_LINE_LENGTH + ROW_PADDING + maximumWidth + BORDER_PADDING;
    final int height = BORDER_PADDING + bodyHeight + BORDER_PADDING;
    setSize(width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    drawInfoBox(g);
    drawLineInfoContents(g);
  }

  private void drawLineInfoContents(Graphics g) {
    final int textHeight = g.getTextHeight();
    int rowY = BORDER_PADDING;
    for (LineModel lineModel : this.dataModel) {
      final Color oldColor = g.getColor();
      final LineType oldType = g.getLineType();
      final float oldLineWidth = g.getLineWidth();

      g.setColor(lineModel.getLineColor());
      g.setLineType(lineModel.getLineType());
      g.setLineWidth(lineModel.getLineWidth());
      final int lineY = rowY + textHeight / 2;
      g.drawLine(BORDER_PADDING, lineY, BORDER_PADDING + SAMPLE_LINE_LENGTH, lineY);
      g.setLineWidth(oldLineWidth);
      g.setColor(oldColor);
      g.setLineType(oldType);

      g.drawString(lineModel.getLabel(), BORDER_PADDING + SAMPLE_LINE_LENGTH + ROW_PADDING, rowY + g.getTextAscent() + BORDER_PADDING);

      rowY += textHeight + ROW_PADDING;
    }
  }

  private void drawInfoBox(Graphics g) {
    Color oldColor = g.getColor();

    drawInfoBoxBackground(g);

    g.setColor(ColorConstants.FUNCTION_INFO_BORDER);
    // 上端、右端は、親の図とわざとかぶらせる
    g.drawRect(0, -1, getWidth(), getHeight() - 1);

    g.setColor(oldColor);
  }

  private void drawInfoBoxBackground(Graphics g) {
    float oldAlpha = g.getAlpha();
    g.setAlpha(0.8f);
    g.setColor(ColorConstants.FUNCTION_INFO_BACKGROUND);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setAlpha(oldAlpha);
  }
}
