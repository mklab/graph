/*
 * Created on 2010/10/16
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.figure.Figure;
import org.mklab.ishikura.graph.g2d.model.LineModel;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 二次元グラフの最小限の描画を行います。
 * <p>
 * 関数、グリッド、関数情報ボックス、グリッドの目盛りの描画を行います。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/16
 */
class SimpleCoordinateSpaceFigure extends ContainerFigureImpl implements CoordinateSpaceFigure {

  /** 目盛りと軸の間の余白です。 */
  private static final int GRADUATION_TO_AXIS_PADDING = 5;

  /** 格子部分の図です。 */
  private GridFigure grid;
  /** 関数を表示する図です。 */
  private FunctionsFigure functionsFigure;
  /** ガイドの表示を行う図です。 */
  private GuideFigure guideFigure;
  /** 関数情報表示ボックスです。 */
  private InfoBoxFigure lineInfoBox;
  /** グラフ上の値を文字列にするために利用します。 */
  private ValueToStringer valueToStringer;

  /** 目盛り文字の色です。 */
  private Color textColor = ColorConstants.GRADUATION_TEXT;
  /** 座標空間の枠の色です。 */
  private Color borderColor = ColorConstants.COORDINATES_BORDER;
  /** 左側の最小スペースです。初回レイアウト時に設定されます。 */
  private int minimumLeftSpace = -1;

  /**
   * {@link SimpleCoordinateSpaceFigure}オブジェクトを構築します。
   */
  SimpleCoordinateSpaceFigure(InfoBoxFigure functionInfoBoxFigure) {
    this.grid = new GridFigure();
    this.functionsFigure = new FunctionsFigure();
    this.guideFigure = new GuideFigure(this.grid);
    this.lineInfoBox = functionInfoBoxFigure;
    this.valueToStringer = new ValueToStringerImpl();

    add(this.grid);
    add(this.functionsFigure);
    add(this.guideFigure);
    add(this.lineInfoBox);

    this.guideFigure.setVisible(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GridFigure getGrid() {
    return this.grid;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layout(Graphics g) {
    if (this.minimumLeftSpace == -1) this.minimumLeftSpace = g.computeTextWidth("0000"); //$NON-NLS-1$

    validateChildren(g);
    final int leftSpaceToGrid = Math.max(this.minimumLeftSpace, this.computePaddingOfYAxisGraduation(g, this.grid.getGridY()));
    final int bottomSpace = computePaddingOfXAxisGraduation(g);
    final int topSpace = 0;

    setGridBounds(leftSpaceToGrid, topSpace, getWidth() - leftSpaceToGrid, getHeight() - bottomSpace - topSpace);
    validateChildren(g);
  }

  private void setGridBounds(int x, int y, int width, int height) {
    this.grid.setBounds(x, y, width, height);
    this.functionsFigure.setBounds(x, y, width, height);
    this.guideFigure.setBounds(x, y, width, height);
    this.lineInfoBox.setX(getWidth() - this.lineInfoBox.getWidth());
    this.lineInfoBox.setY(0);
  }

  /**
   * x軸の目盛り描画領域の余白を計算します。
   * 
   * @param g 文字幅取得に利用するグラフィックス
   * @param gridForYAxis y軸の目盛り
   * @return y軸の目盛り描画領域の余白
   */
  private int computePaddingOfXAxisGraduation(Graphics g) {
    return g.getTextHeight() + GRADUATION_TO_AXIS_PADDING;
  }

  /**
   * y軸の目盛り描画領域の余白を計算します。
   * 
   * @param g 文字幅取得に利用するグラフィックス
   * @param gridForYAxis y軸の目盛り
   * @return y軸の目盛り描画領域の余白
   */
  private int computePaddingOfYAxisGraduation(Graphics g, final Grid gridForYAxis) {
    int maximumScaleWidth = Integer.MIN_VALUE;
    for (final Double y : gridForYAxis) {
      final int w = g.computeTextWidth(this.valueToStringer.valueToString(y.doubleValue()));
      if (maximumScaleWidth < w) maximumScaleWidth = w;
    }
    if (maximumScaleWidth < 0) return 50;
    return maximumScaleWidth + GRADUATION_TO_AXIS_PADDING;
  }

  /**
   * 表示範囲を設定します。
   * 
   * @param scope 表示範囲
   */
  @Override
  public void setScope(Scope scope) {
    this.grid.setScope(scope);
    invalidate();
  }

  /**
   * {@inheritDoc} int)
   */
  @Override
  public void moveScope(final int dx, final int dy) {
    this.grid.moveScope(dx, dy);
    invalidate();
  }

  /**
   * {@inheritDoc} int, double)
   */
  @Override
  public void scaleScope(final int x, final int y, double ratio) {
    this.grid.scaleScope(x - this.grid.getX(), y - this.grid.getY(), ratio);
    invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    final Color oldColor = g.getColor();

    drawChildren(g);
    drawBorder(g);
    drawGraduations(g);

    g.setColor(oldColor);
  }

  private void drawBorder(Graphics g) {
    g.setColor(this.borderColor);
    g.drawRect(this.grid.getX(), this.grid.getY(), this.grid.getWidth() - 1, this.grid.getHeight() - 1);
  }

  private void drawGraduations(Graphics g) {
    g.setColor(this.textColor);
    final int paddingX = this.grid.getX();
    final int paddingY = this.grid.getY();
    g.translate(paddingX, paddingY);
    drawGraduationsForX(g);
    drawGraduationsForY(g);
    g.translate(-paddingX, -paddingY);
  }

  private void drawGraduationsForX(Graphics g) {
    for (final Double modelX : this.grid.getGridX()) {
      final int viewX = this.grid.modelToViewX(modelX.doubleValue());

      if (viewX == -1) continue;
      final String graduation = toGraduationText(modelX.doubleValue());
      final int graduationWidth = g.computeTextWidth(graduation);

      final int x = viewX - graduationWidth / 2;
      final int y = this.grid.getHeight() + g.getTextAscent() + GRADUATION_TO_AXIS_PADDING;

      g.setColor(this.textColor);
      g.drawString(graduation, x, y);
    }
  }

  private void drawGraduationsForY(Graphics g) {
    for (final Double modelY : this.grid.getGridY()) {
      int viewY = this.grid.modelToViewY(modelY.doubleValue());
      if (viewY == -1) break;
      final String graduation = toGraduationText(modelY.doubleValue());
      final int graduationWidth = g.computeTextWidth(graduation);

      final int x = -graduationWidth - GRADUATION_TO_AXIS_PADDING;
      final int y = viewY + (g.getTextAscent() - g.getTextDescent()) / 2;

      g.setColor(this.textColor);
      g.drawString(graduation, x, y);
    }
  }

  private String toGraduationText(double value) {
    return this.valueToStringer.valueToString(value);
  }

  /**
   * 線を追加します。
   * 
   * @param lineModel 線のモデル
   */
  @Override
  public void addLine(LineModel lineModel) {
    if (lineModel == null) throw new NullPointerException();
    final FunctionFigure figure = new FunctionFigure(this.grid, lineModel);
    this.functionsFigure.add(figure);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeLine(LineModel lineModel) {
    Figure willRemove = null;
    for (Figure figure : getChildren()) {
      if (figure instanceof FunctionFigure) {
        final FunctionFigure functionFigure = (FunctionFigure)figure;
        if (functionFigure.getLineModel() == lineModel) {
          willRemove = functionFigure;
          break;
        }
      }
    }
    if (willRemove == null) throw new IllegalArgumentException("Specified lineModel was not contained in this figure."); //$NON-NLS-1$

    remove(willRemove);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public InfoBoxFigure getInfoBox() {
    return this.lineInfoBox;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setBorderColor(Color color) {
    this.borderColor = color;
  }
}
