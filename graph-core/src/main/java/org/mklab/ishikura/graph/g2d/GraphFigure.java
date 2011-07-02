/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.figure.Figures;
import org.mklab.ishikura.graph.figure.Point;
import org.mklab.ishikura.graph.figure.TextFigure;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 二次元グラフを表すクラスです。
 * <p>
 * このクラスは、基本的なグラフ機能を備えたクラスです。<br>
 * グラフのタイトル、x軸、y軸のタイトル、ユーザー通知領域として利用できるステータスバーを提供します。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public class GraphFigure extends ContainerFigureImpl {

  /** ステータス描画を行う図です。 */
  private TextFigure statusBar;
  /** 座標系の図です。 */
  private LabeledCoordinateSpaceFigure coordinateSpace;

  /**
   * {@link GraphFigure}オブジェクトを構築します。
   */
  public GraphFigure() {
    this.statusBar = new TextFigure();
    final CoordinateSpaceFigure baseCoordinateSpace = new SimpleCoordinateSpaceFigure();
    this.coordinateSpace = new LabeledCoordinateSpaceFigure(baseCoordinateSpace);

    add(this.statusBar);
    add(this.coordinateSpace);

    setBackgroundColor(ColorConstants.BACKGROUND);
    this.coordinateSpace.getGrid().setBackgroundColor(ColorConstants.COORDINATES_BACKGROUND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layout(Graphics g) {
    final int statusBarHeight = g.getTextHeight();
    final int rightSpace = 10;

    this.coordinateSpace.setBounds(0, 0, getWidth() - rightSpace, getHeight() - statusBarHeight);
    this.statusBar.setBounds(0, getHeight() - statusBarHeight, getWidth(), statusBarHeight);

    validateChildren(g);
  }

  /**
   * 関数を設定します。
   * 
   * @param functions 関数
   */
  public void setFunctions(Function2D[] functions) {
    for (Function2D f : functions) {
      final FunctionFigure fig = this.coordinateSpace.newFunctionFigure();
      fig.setFunction(f);
    }
  }

  /**
   * 表示範囲を設定します。
   * 
   * @param startX 開始x値
   * @param endX 終了x値
   * @param startY 開始y値
   * @param endY 終了y値
   */
  public void setScope(double startX, double endX, double startY, double endY) {
    this.coordinateSpace.setScope(new Scope(startX, endX, startY, endY));
  }

  /**
   * ステータスメッセージを設定します。
   * 
   * @param msg ステータスメッセージ
   */
  public void setStatus(String msg) {
    this.statusBar.setText(msg);
  }

  /**
   * 座標空間を取得します。
   * 
   * @return 座標空間
   */
  public CoordinateSpaceFigure getCoordinateSpace() {
    return this.coordinateSpace;
  }

  /**
   * グラフタイトルを設定します。
   * 
   * @param graphTitle グラフのタイトル
   */
  public void setTitle(String graphTitle) {
    this.coordinateSpace.setTitle(graphTitle);
  }

  /**
   * X軸の名前を設定します。
   * 
   * @param name x軸の名前
   */
  public void setNameOfX(String name) {
    this.coordinateSpace.setNameOfX(name);
  }

  /**
   * Y軸の名前を設定します。
   * 
   * @param name y軸の名前
   */
  public void setNameOfY(String name) {
    this.coordinateSpace.setNameOfY(name);
  }

  /**
   * 表示位置を移動します。
   * 
   * @param dx x方向の移動量
   * @param dy y方向の移動量
   */
  public void move(final int dx, final int dy) {
    this.coordinateSpace.moveScope(dx, dy);
    invalidate();
  }

  /**
   * 与えられた座標を中心として拡大を行います。
   * 
   * @param x 中心とするx座標
   * @param y 中心とするy座標
   * @param ratio 拡大率
   */
  public void scale(final int x, final int y, double ratio) {
    this.coordinateSpace.scaleScope(x - this.coordinateSpace.getX(), y - this.coordinateSpace.getY(), ratio);
    invalidate();
  }

  /**
   * 表示範囲を設定します。
   * 
   * @param scope 表示範囲
   */
  public void setScope(Scope scope) {
    this.coordinateSpace.setScope(scope);
    invalidate();
  }

  /**
   * ビューのx座標からモデルの座標へ変換します。
   * 
   * @param x x座標
   * @return モデルのx座標
   */
  public double viewToModelX(int x) {
    final Point pointOnThisFigure = new Point(x, 0);
    final Point pointOnGrid = Figures.convertPoint(this, getGrid(), pointOnThisFigure);
    final int xOnGrid = pointOnGrid.x;

    final double viewX = getGrid().viewToModelX(xOnGrid);
    return viewX;
  }

  /**
   * ビューのy座標からモデルの座標へ変換します。
   * 
   * @param y y座標
   * @return モデルのy座標
   */
  public double viewToModelY(int y) {
    final Point pointOnThisFigure = new Point(0, y);
    final Point pointOnGrid = Figures.convertPoint(this, getGrid(), pointOnThisFigure);
    final int yOnGrid = pointOnGrid.y;

    final double viewY = getGrid().viewToModelY(yOnGrid);
    return viewY;
  }

  /**
   * ビュー上の与えられた座標が、座標空間の領域に含まれるかどうか調べます。
   * 
   * @param x x座標
   * @param y y座標
   * @return 含まれていればtrue,そうでなければfalse
   */
  public boolean containsInCoordinateSpace(int x, int y) {
    final GridFigure grid = getGrid();
    final Point pointOnGrid = Figures.convertPoint(this, grid, new Point(x, y));
    return grid.contains(pointOnGrid.x, pointOnGrid.y);
  }

  /**
   * グリッドを取得します。
   * 
   * @return グリッド
   */
  private GridFigure getGrid() {
    return this.coordinateSpace.getGrid();
  }

}
