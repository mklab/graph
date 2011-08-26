/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.abgr.Color;
import org.mklab.abgr.Graphics;
import org.mklab.ishikura.graph.figure.AbstractFigure;


/**
 * ガイドの表示を行う図を表すクラスです。
 * <p>
 * ガイドとは、グラフ上をマウスが動くとそれに応じてそのx座標とy座標を線で知らせてくれるあれです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/23
 */
public class GuideFigure extends AbstractFigure {

  /** ガイドの線色です。 */
  private Color lineColor = ColorConstants.GUIDE_LINE;
  /** グリッド部分の図です。座標変換に利用します。 */
  private GridFigure grid;
  /** ガイドのx座標(モデル上)です。 */
  private double guideX;
  /** ガイドのy座標(モデル上)です。 */
  private double guideY;

  /**
   * {@link GuideFigure}オブジェクトを構築します。
   * 
   * @param grid グリッド
   */
  public GuideFigure(GridFigure grid) {
    if (grid == null) throw new NullPointerException();
    this.grid = grid;
  }

  /**
   * ガイドのx座標を設定します。
   * 
   * @param guideX ガイドのx座標
   */
  public void setGuideX(double guideX) {
    this.guideX = guideX;
  }

  /**
   * ガイドのy座標を設定します。
   * 
   * @param guideY ガイドのy座標
   */
  public void setGuideY(double guideY) {
    this.guideY = guideY;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    final int x = this.grid.modelToViewX(this.guideX);
    final int y = this.grid.modelToViewY(this.guideY);

    g.setColor(this.lineColor);
    g.drawLine(x, 0, x, getHeight() - 1);
    g.drawLine(0, y, getWidth() - 1, y);
  }

}
