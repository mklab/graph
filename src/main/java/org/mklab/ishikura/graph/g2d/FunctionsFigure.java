/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 複数の関数を表示する図です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
final class FunctionsFigure extends ContainerFigureImpl {

  FunctionFigure[] functionFigures;
  GridFigure gridFigure;

  /**
   * {@link FunctionsFigure}オブジェクトを構築します。
   * 
   * @param gridFigure グリッド図
   */
  FunctionsFigure(GridFigure gridFigure) {
    if (gridFigure == null) throw new NullPointerException();
    this.functionFigures = new FunctionFigure[0];
    this.gridFigure = gridFigure;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layout(Graphics g) {
    for (final FunctionFigure functionFigure : this.functionFigures) {
      functionFigure.setBounds(0, 0, getWidth(), getHeight());
    }

    super.layout(g);
  }

  /**
   * 関数を設定します。
   * <p>
   * このメソッドは、すでに生成された{@link FunctionFigure}をすべてクリアします。
   * 
   * @param functions 関数
   */
  public void setFunctions(Function2D[] functions) {
    clear();

    this.functionFigures = new FunctionFigure[functions.length];
    for (int i = 0; i < functions.length; i++) {
      this.functionFigures[i] = new FunctionFigure(this.gridFigure);
      this.functionFigures[i].setFunction(functions[i]);
      add(this.functionFigures[i]);
    }
  }

}
