/**
 * 
 */
package org.mklab.graph.g2d;

import java.util.ArrayList;
import java.util.List;

import org.mklab.abgr.Color;
import org.mklab.abgr.Graphics;
import org.mklab.abgr.LineType;
import org.mklab.graph.figure.AbstractFigure;
import org.mklab.graph.figure.Figure;
import org.mklab.graph.figure.Figures;
import org.mklab.graph.figure.Point;


/**
 * グラフの格子を表すクラスです。
 * <p>
 * 関数の背景に描画する格子を描画する責任、及び関数の具体的な値から、グラフ上の座標に変換する責任を担います。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public final class GridFigure extends AbstractFigure implements HasCoordinateSpace {

  /** 数学上(モデル上)での値の範囲です。 */
  private Scope scope;
  /** x方向のグリッドを生成するファクトリです。 */
  private GridFactory gridFactoryX;
  /** y方向のグリッドを生成するファクトリです。 */
  private GridFactory gridFactoryY;
  /** x方向のグリッドです。 */
  private Grid gridX;
  /** y方向のグリッドです。 */
  private Grid gridY;
  /** x軸方向の座標変換を行うオブジェクトです。 */
  Measure measureX;
  /** y軸方向の座標変換を行うオブジェクトです。 */
  Measure measureY;
  /** 軸、及びボーダーの色です。 */
  private Color axisColor = ColorConstants.AXIS;
  /** グリッドの色です。 */
  private Color gridColor = ColorConstants.GRID;
  /** マイナーグリッドの描画に用いる色です。 */
  private Color minorGridColor = ColorConstants.MINOR_GRID;
  /** グリッドの描画を有効にするかどうかを示します。 */
  private boolean gridEnabled = true;
  /** マイナーグリッドの描画を有効にするかどうかを示します。 */
  private boolean minorGridEnabled = true;

  /**
   * {@link GridFigure}オブジェクトを構築します。
   */
  public GridFigure() {
    this.measureX = new StandardMeasure();
    this.measureY = new StandardMeasure();
    this.gridFactoryX = new StandardGridFactory();
    this.gridFactoryY = new StandardGridFactory();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setScope(Scope scope) {
    if (scope == null) throw new NullPointerException();
    this.measureX.setBound(scope.getX());
    this.measureY.setBound(scope.getY());

    this.scope = scope;
    invalidate();
  }

  /**
   * 表示範囲を取得します。
   * 
   * @return 表示範囲
   */
  public Scope getScope() {
    return this.scope;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveScope(final int dx, final int dy) {
    final double modelDx = dx * this.measureX.getViewToModelRatio();
    final double modelDy = dy * this.measureY.getViewToModelRatio();

    setScope(this.scope.translatedScope(modelDx, modelDy));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void scaleScope(final int x, final int y, double ratio) {
    final double modelX = viewToModelX(x);
    final double modelY = viewToModelY(y);

    if (modelX == Double.NaN || modelY == Double.NaN) throw new InvalidScopeException();

    setScope(this.scope.scaledScope(modelX, modelY, ratio));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void layout(Graphics g) {
    assertScopeIsSet();
    super.layout(g);

    this.gridX = this.gridFactoryX.create(this.scope.getX().getStart(), this.scope.getX().getEnd());
    this.gridY = this.gridFactoryY.create(this.scope.getY().getStart(), this.scope.getY().getEnd());

    this.measureX.setViewSize(getWidth());
    this.measureY.setViewSize(getHeight());
  }

  /**
   * x方向のグリッドを取得します。
   * 
   * @return x方向のグリッド
   */
  public Grid getGridX() {
    return this.gridX;
  }

  /**
   * y方向のグリッドを取得します。
   * 
   * @return y方向のグリッド
   */
  public Grid getGridY() {
    return this.gridY;
  }

  /**
   * 軸の色を取得します。
   * 
   * @return 軸の色
   */
  public Color getAxisColor() {
    return this.axisColor;
  }

  /**
   * 軸の色を設定します。
   * 
   * @param axisColor 軸の色
   */
  public void setAxisColor(Color axisColor) {
    if (axisColor == null) throw new NullPointerException();
    this.axisColor = axisColor;
  }

  /**
   * グリッド描画色を取得します。
   * 
   * @return グリッド描画色
   */
  public Color getGridColor() {
    return this.gridColor;
  }

  /**
   * グリッド描画色を設定します。
   * 
   * @param gridColor グリッドの色
   */
  public void setGridColor(Color gridColor) {
    if (gridColor == null) throw new NullPointerException();
    this.gridColor = gridColor;
  }

  /**
   * マイナーグリッド描画色を取得します。
   * 
   * @return マイナーグリッド描画色
   */
  public Color getMinorGridColor() {
    return this.minorGridColor;
  }

  /**
   * マイナーグリッド描画色を設定します。
   * 
   * @param minorGridColor マイナーグリッドの色
   */
  public void setMinorGridColor(Color minorGridColor) {
    if (minorGridColor == null) throw new NullPointerException();
    this.minorGridColor = minorGridColor;
  }

  /**
   * グリッドの描画が有効であるかを取得します。
   * 
   * @return グリッドの描画が有効であるかどうか
   */
  public boolean isGridEnabled() {
    return this.gridEnabled;
  }

  /**
   * グリッドの描画の有効/無効を設定します。
   * 
   * @param gridEnabled {@code true}ならば有効、{@code false}ならば無効
   */
  public void setGridEnabled(boolean gridEnabled) {
    this.gridEnabled = gridEnabled;
  }

  /**
   * マイナーグリッドの描画が有効であるかを取得します。
   * 
   * @return マイナーグリッドの描画が有効であるかどうか
   */
  public boolean isMinorGridEnabled() {
    return this.minorGridEnabled;
  }

  /**
   * マイナーグリッドの描画の有効/無効を設定します。
   * 
   * @param minorGridEnabled {@code true}ならば有効、{@code false}ならば無効
   */
  public void setMinorGridEnabled(boolean minorGridEnabled) {
    this.minorGridEnabled = minorGridEnabled;
  }

  /**
   * モデルのx座標からビューの座標へ変換します。
   * 
   * @param x x座標
   * @return ビューのx座標。グリッドの範囲外であった場合は-1
   */
  public int modelToViewX(double x) {
    final int viewX = this.measureX.modelToView(x);
    if (viewX == -1) return -1;
    return viewX;
  }

  /**
   * モデルのx座標からビューの座標へ変換します
   * 
   * @param x x座標
   * @return ビューのx座標
   */
  public int modelToViewXIgnoreBound(double x) {
    return this.measureX.modelToViewIgnoreBound(x);
  }

  /**
   * モデルのy座標からビューの座標へ変換します。
   * 
   * @param from 図
   * @param y この図上のy座標
   * @return この図上のビューのy座標。グリッド外の場合は-1
   */
  public int modelToViewY(Figure from, double y) {
    return modelToViewY(from, y, false);
  }

  /**
   * モデルのy座標からビューの座標へ変換します。
   * <p>
   * この図上のy座標でないと正しく変換できません。
   * 
   * @param y この図上のy座標
   * @return この図上のビューのy座標。グリッド外ならば-1を返します。
   */
  public int modelToViewY(double y) {
    return modelToViewY(this, y);
  }

  /**
   * モデルのy座標からビューの座標へ変換します。
   * <p>
   * この図上のy座標でないと正しく変換できません。
   * 
   * @param y この図上のy座標
   * @return この図上のビューのy座標
   */
  public int modelToViewYIgnoreBound(double y) {
    return modelToViewY(this, y, true);
  }

  /**
   * from上のy座標からビューの座標へ変換します。
   * 
   * @param from 図
   * @param y この図上のy座標
   * @param ignoreBound trueならば範囲外でも計算し、falseであれば範囲外の場合-1を返します。
   * @return この図上のビューのy座標。範囲外ならば-1
   */
  private int modelToViewY(Figure from, double y, boolean ignoreBound) {
    int viewY = ignoreBound ? this.measureY.modelToViewIgnoreBound(y) : this.measureY.modelToView(y);
    if (ignoreBound == false && viewY == -1) return -1;
    viewY = Figures.convertPoint(from, this, new Point(0, viewY)).y;
    return getHeight() - viewY - 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModelX(int x) {
    final double modelValue = this.measureX.viewToModel(x);
    if (Double.isNaN(modelValue)) throw new InvalidScopeException();
    return modelValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double viewToModelY(int y) {
    final double modelValue = this.measureY.viewToModel(getHeight() - y - 1);
    if (Double.isNaN(modelValue)) throw new InvalidScopeException();
    return modelValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    assertScopeIsSet();

    final Color oldColor = g.getColor();

    // grid
    drawGrid(g);

    // zero lines
    g.setColor(getAxisColor());
    drawZeroLines(g);

    g.setColor(oldColor);
  }

  private void assertScopeIsSet() {
    if (this.scope == null) throw new IllegalStateException("Scope not set."); //$NON-NLS-1$
  }

  /**
   * グリッドを描画します。
   * 
   * @param g 描画対象
   */
  private void drawGrid(Graphics g) {
    g.setLineType(LineType.DOT);

    drawGridsForX(g);
    drawGridsForY(g);

    g.setLineType(LineType.DEFAULT);
  }

  /**
   * x軸のグリッドを描画します。
   * 
   * @param g 描画対象
   */
  private void drawGridsForX(Graphics g) {
    if (this.gridX == null) throw new NullPointerException("x grid was not set."); //$NON-NLS-1$

    if (isMinorGridEnabled()) {
      g.setColor(this.minorGridColor);
      drawVerticalLines(g, computeMinorLines(this.gridX));
    }
    if (isGridEnabled()) {
      g.setColor(this.gridColor);
      drawVerticalLines(g, this.gridX);
    }
  }

  /**
   * y軸のグリッドを描画します。
   * 
   * @param g 描画対象
   */
  private void drawGridsForY(Graphics g) {
    if (this.gridY == null) throw new NullPointerException("y grid was not set."); //$NON-NLS-1$

    if (isMinorGridEnabled()) {
      g.setColor(this.minorGridColor);
      drawHorizontalLines(g, computeMinorLines(this.gridY));
    }
    if (isGridEnabled()) {
      g.setColor(this.gridColor);
      drawHorizontalLines(g, this.gridY);
    }
  }

  /**
   * 鉛直方向の直線を描画します。
   * 
   * @param g 描画対象
   * @param xValues 直線のx座標群
   */
  private void drawVerticalLines(Graphics g, Iterable<Double> xValues) {
    for (final Double modelX : xValues) {
      int viewX = modelToViewX(modelX.doubleValue());
      if (viewX == -1) continue;

      g.drawLine(viewX, 0, viewX, getHeight() - 1);
    }
  }

  /**
   * 水平方向の直線を描画します。
   * 
   * @param g 描画対象
   * @param yValues 直線のy座標群
   */
  private void drawHorizontalLines(Graphics g, Iterable<Double> yValues) {
    for (final Double modelY : yValues) {
      int viewY = modelToViewY(modelY.doubleValue());
      if (viewY == -1) continue;

      g.drawLine(0, viewY, getWidth() - 1, viewY);
    }
  }

  /**
   * 与えられたグリッドをさらに分割するマイナーグリッドを生成します。
   * 
   * @param grid 元にするグリッド
   * @return グリッドをさらに分割したマイナーグリッド
   */
  private Iterable<Double> computeMinorLines(Grid grid) {
    final List<Double> minorLines = new ArrayList<Double>();
    Double previous = null;
    for (Double d : grid) {
      if (previous == null) {
        previous = d;
        continue;
      }
      final double minorLineInterval = (d.doubleValue() - previous.doubleValue()) / 10;
      for (int i = 1; i < 10; i++) {
        minorLines.add(Double.valueOf(previous.doubleValue() + minorLineInterval * i));
      }
      previous = d;
    }
    return minorLines;
  }

  /**
   * x,yの値が0の基準線を描画します。
   * 
   * @param g 描画先グラフィックス
   */
  private void drawZeroLines(Graphics g) {
    final int zeroLineX = modelToViewX(0);
    if (zeroLineX != -1) {
      g.drawLine(zeroLineX, 0, zeroLineX, getHeight() - 1);
    }

    final int zeroLineY = modelToViewY(0);
    if (zeroLineY != -1) {
      g.drawLine(0, zeroLineY, getWidth() - 1, zeroLineY);
    }
  }

}
