/*
 * Created on 2010/10/16
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.abgr.Color;
import org.mklab.abgr.Graphics;
import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.g2d.model.LineModel;


/**
 * 二次元グラフの最小限の描画を行います。
 * <p>
 * 関数、グリッド、関数情報ボックス、グリッドの目盛りの描画を行います。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/16
 */
class CoordinateSpaceFigure extends ContainerFigureImpl implements HasCoordinateSpace {

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
   * {@link CoordinateSpaceFigure}オブジェクトを構築します。
   */
  CoordinateSpaceFigure(InfoBoxFigure functionInfoBoxFigure) {
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
      if (viewY == -1) continue;
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
   * {@inheritDoc}
   */
  @Override
  public void moveScope(final int dx, final int dy) {
    this.grid.moveScope(dx, dy);
    invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void scaleScope(final int x, final int y, double ratio) {
    Util.scaleScope(this, this.grid, x, y, ratio);
    invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModelX(int x) {
    return Util.viewToModelX(this, this.grid, x);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModelY(int y) {
    return Util.viewToModelY(this, this.grid, y);
  }

  /**
   * 格子部分の図を取得します。
   * 
   * @return グリッド
   */
  GridFigure getGrid() {
    return this.grid;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setScope(Scope scope) {
    this.grid.setScope(scope);
    invalidate();
  }

  /**
   * 線を追加します。
   * 
   * @param lineModel 線のモデル
   */
  void addLine(LineModel lineModel) {
    if (lineModel == null) throw new NullPointerException();
    final FunctionFigure figure = new FunctionFigure(this.grid, lineModel);
    this.functionsFigure.addLine(lineModel, figure);

    this.lineInfoBox.invalidate();
    invalidate();
  }

  /**
   * 線を削除します。
   * 
   * @param lineModel 線のモデル
   */
  void removeLine(LineModel lineModel) {
    this.functionsFigure.removeLine(lineModel);
    this.lineInfoBox.invalidate();
    invalidate();
  }

  /**
   * 関数情報表示を行う図を取得します。
   * 
   * @return 関数情報表示ボックス
   */
  InfoBoxFigure getInfoBox() {
    return this.lineInfoBox;
  }

  /**
   * 枠の色を設定します。
   * 
   * @param color 枠の色
   */
  void setBorderColor(Color color) {
    this.borderColor = color;
  }

  /**
   * 枠の色を取得します。
   * 
   * @param color 枠の色
   */
  Color getBorderColor() {
    return this.borderColor;
  }

}
