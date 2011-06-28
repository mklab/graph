/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.figure.TextAlignment;
import org.mklab.ishikura.graph.figure.TextFigure;
import org.mklab.ishikura.graph.figure.TextOrientation;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * ラベル付きの座標空間の図です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public class LabeledCoordinateSpaceFigure extends ContainerFigureImpl implements CoordinateSpaceFigure {

  private CoordinateSpaceFigure coordinateSpace;
  private TextFigure xLabel;
  private TextFigure yLabel;
  private TextFigure titleLabel;

  /**
   * {@link LabeledCoordinateSpaceFigure}オブジェクトを構築します。
   * 
   * @param coodinateSpace 座標系の図
   */
  public LabeledCoordinateSpaceFigure(CoordinateSpaceFigure coodinateSpace) {
    if (coodinateSpace == null) throw new NullPointerException();
    this.coordinateSpace = coodinateSpace;

    this.titleLabel = new TextFigure();
    this.titleLabel.setAlignment(TextAlignment.CENTER);

    this.xLabel = new TextFigure();
    this.xLabel.setAlignment(TextAlignment.CENTER);

    this.yLabel = new TextFigure();
    this.yLabel.setAlignment(TextAlignment.CENTER);
    this.yLabel.setOrientation(TextOrientation.VERTICAL);

    add(this.coordinateSpace);
    add(this.xLabel);
    add(this.yLabel);
    add(this.titleLabel);
  }

  /**
   * {@link LabeledCoordinateSpaceFigure}オブジェクトを構築します。
   */
  public LabeledCoordinateSpaceFigure() {
    this(new SimpleCoordinateSpaceFigure());
  }

  /**
   * グラフのタイトルを設定します。
   * 
   * @param graphTitle グラフのタイトル
   */
  public void setTitle(String graphTitle) {
    this.titleLabel.setText(graphTitle);
  }

  /**
   * グラフのタイトルを取得します。
   * 
   * @return グラフのタイトル
   */
  public String getTitle() {
    return this.titleLabel.getText();
  }

  /**
   * x軸の名前を設定します。
   * 
   * @param name x軸の名前
   */
  public void setNameOfX(String name) {
    this.xLabel.setText(name);
  }

  /**
   * x軸の名前を取得します。
   * 
   * @return　x軸の名前
   */
  public String getNameOfX() {
    return this.xLabel.getText();
  }

  /**
   * y軸の名前を設定します。
   * 
   * @param name y軸の名前
   */
  public void setNameOfY(String name) {
    this.yLabel.setText(name);
  }

  /**
   * y軸の名前を取得します。
   * 
   * @return　y軸の名前
   */
  public String getNameOfY() {
    return this.yLabel.getText();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layout(Graphics g) {
    final int textHeight = g.getTextHeight();

    final int left = textHeight;
    final int titleBottomSpace = 10;
    final int top = textHeight + titleBottomSpace;
    final int bottom = textHeight;

    this.coordinateSpace.setBounds(left, top, getWidth() - textHeight, getHeight() - bottom - top);
    this.titleLabel.setBounds(left, 0, this.coordinateSpace.getWidth(), top);
    this.xLabel.setBounds(left, top + this.coordinateSpace.getHeight(), this.coordinateSpace.getWidth(), bottom);
    this.yLabel.setBounds(0, 0, left, this.coordinateSpace.getHeight());

    validateChildren(g);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GridFigure getGrid() {
    return this.coordinateSpace.getGrid();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveScope(int dx, int dy) {
    this.coordinateSpace.moveScope(dx, dy);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void scaleScope(int x, int y, double ratio) {
    this.coordinateSpace.scaleScope(x - this.coordinateSpace.getX(), y - this.coordinateSpace.getY(), ratio);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setScope(Scope scope) {
    this.coordinateSpace.setScope(scope);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FunctionFigure newFunctionFigure() {
    return this.coordinateSpace.newFunctionFigure();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFunctionFigure(FunctionFigure figure) {
    this.coordinateSpace.removeFunctionFigure(figure);
  }
}
