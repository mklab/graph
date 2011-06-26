/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.AbstractFigure;
import org.mklab.ishikura.graph.figure.Figure;
import org.mklab.ishikura.graph.figure.Figures;
import org.mklab.ishikura.graph.figure.Point;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;
import org.mklab.ishikura.graph.graphics.LineType;


/**
 * グラフの格子を表すクラスです。
 * <p>
 * 関数の具体的な値から、グラフ上の座標に変換する責任を担います。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class GridFigure extends AbstractFigure {

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

  /**
   * {@link GridFigure}オブジェクトを構築します。
   */
  public GridFigure() {
    this.measureX = new StandardMeasure();
    this.measureY = new LogScaleMeasure();
    this.gridFactoryX = new GridFactoryImpl();
    this.gridFactoryY = new LogScaleGridFactory();
  }

  /**
   * 表示範囲を設定します。
   * 
   * @param scope 表示範囲
   */
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
   * 表示位置を移動します。
   * 
   * @param dx x方向の移動量
   * @param dy y方向の移動量
   */
  public void moveScope(final int dx, final int dy) {
    final double modelDx = dx * this.measureX.getViewToModelRatio();
    final double modelDy = dy * this.measureY.getViewToModelRatio();

    setScope(this.scope.translatedScope(modelDx, modelDy));
  }

  /**
   * 与えられた座標を中心として拡大を行います。
   * 
   * @param x 中心とするx座標
   * @param y 中心とするy座標
   * @param ratio 拡大率
   */
  public void scaleScope(final int x, final int y, double ratio) {
    final double modelX = this.measureX.viewToModel(x);
    final double modelY = this.measureY.viewToModel(y);

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
    this.axisColor = axisColor;
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
   * ビューのx座標からモデルの座標へ変換します。
   * 
   * @param x x座標
   * @return モデルのx座標
   */
  public double viewToModelX(int x) {
    return this.measureX.viewToModel(x);
  }

  /**
   * ビュー上のy座標からモデルの座標へ変換します。
   * 
   * @param y y座標
   * @return モデルのy座標
   */
  public double viewToModelY(int y) {
    return this.measureY.viewToModel(getHeight() - y - 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    assertScopeIsSet();

    final Color oldColor = g.getColor();
    // zero lines
    g.setColor(getAxisColor());
    drawZeroLines(g);

    // grid
    drawGrid(g);
    g.setColor(oldColor);
  }

  private void assertScopeIsSet() {
    if (this.scope == null) throw new IllegalStateException("Scope not set."); //$NON-NLS-1$
  }

  private void drawGrid(Graphics g) {
    g.setColor(this.gridColor);
    g.setLineType(LineType.DOT);

    drawGridsX(g);
    drawGridsY(g);

    g.setLineType(LineType.DEFAULT);
  }

  private void drawGridsX(Graphics g) {
    if (this.gridX == null) throw new NullPointerException("x grid was not set."); //$NON-NLS-1$

    for (final Double modelX : this.gridX) {
      int viewX = modelToViewX(modelX.doubleValue());
      if (viewX == -1) continue;

      g.drawLine(viewX, 0, viewX, getHeight() - 1);
    }
  }

  private void drawGridsY(Graphics g) {
    if (this.gridY == null) throw new NullPointerException("y grid was not set."); //$NON-NLS-1$

    for (final Double modelY : this.gridY) {
      int viewY = modelToViewY(modelY.doubleValue());
      if (viewY == -1) continue;

      g.drawLine(0, viewY, getWidth() - 1, viewY);
    }
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
