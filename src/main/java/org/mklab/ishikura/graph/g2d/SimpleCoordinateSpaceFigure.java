/*
 * Created on 2010/10/16
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import java.text.MessageFormat;

import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 2次元の座標空間を表すクラスです。
 * <p>
 * 数学上の値での表示範囲、scopeを元に座標空間を描画します。 この図以外の2次元の図が座標を参照する場合には必ずこのインスタンスを利用してください。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/16
 */
public class SimpleCoordinateSpaceFigure extends ContainerFigureImpl implements CoordinateSpaceFigure {

  /** 目盛りと軸の間の余白です。 */
  private static final int GRADUATION_TO_AXIS_PADDING = 5;

  /** 格子部分の図です。 */
  private GridFigure grid;
  /** 関数を表示する図です。 */
  private FunctionsFigure functionsFigure;
  /** ガイドの表示を行う図です。 */
  private GuideFigure guideFigure;
  /** 関数情報表示ボックスです。 */
  private FunctionInfoBoxFigure lineInfoBox;

  /** 目盛り文字の色です。 */
  private Color textColor = ColorConstants.GRADUATION_TEXT;
  /** 左側の最小スペースです。初回レイアウト時に設定されます。 */
  private int minimumLeftSpace = -1;
  /** 関数の数です。デフォルト関数名の命名に利用します。 */
  private int functionCount = 1;

  /**
   * {@link SimpleCoordinateSpaceFigure}オブジェクトを構築します。
   */
  public SimpleCoordinateSpaceFigure() {
    this.grid = new GridFigure();
    this.functionsFigure = new FunctionsFigure();
    this.guideFigure = new GuideFigure(this.grid);
    this.lineInfoBox = new FunctionInfoBoxFigure();

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
    validateChildren(g);

    if (this.minimumLeftSpace == -1) this.minimumLeftSpace = g.computeTextWidth("0000"); //$NON-NLS-1$

    final int leftSpaceToGrid = Math.max(this.minimumLeftSpace, this.computePaddingOfYAxisGraduation(g, this.grid.getGridY()));
    final int bottomSpace = computePaddingOfXAxisGraduation(g);
    final int topSpace = 0;

    setGridBounds(leftSpaceToGrid, topSpace, getWidth() - leftSpaceToGrid, getHeight() - bottomSpace - topSpace);
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
      final int w = g.computeTextWidth(y.toString());
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
    g.setColor(ColorConstants.COORDINATES_BORDER);
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
    if (value == (int)value) return String.valueOf((int)value);
    return String.valueOf(value);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("boxing")
  @Override
  public FunctionFigure newFunctionFigure() {
    final FunctionFigure figure = new FunctionFigure(this.grid);
    figure.setLineName(MessageFormat.format("Function {0}", this.functionCount++)); //$NON-NLS-1$
    this.functionsFigure.add(figure);
    this.lineInfoBox.addLineInfo(figure);
    return figure;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFunctionFigure(FunctionFigure figure) {
    this.functionsFigure.remove(figure);
  }

}
