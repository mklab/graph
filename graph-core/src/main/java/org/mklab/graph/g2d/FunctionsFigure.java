/**
 * 
 */
package org.mklab.graph.g2d;

import java.util.HashMap;
import java.util.Map;

import org.mklab.abgr.Graphics;
import org.mklab.graph.figure.ContainerFigureImpl;
import org.mklab.graph.figure.Figure;
import org.mklab.graph.g2d.model.LineModel;


/**
 * 複数の関数を束ねる図です。
 * <p>
 * すべての子の図を、この図と同じサイズにします。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
final class FunctionsFigure extends ContainerFigureImpl {

  Map<LineModel, FunctionFigure> map = new HashMap<LineModel, FunctionFigure>();

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layout(Graphics g) {
    for (final Figure figure : getChildren()) {
      figure.setBounds(0, 0, getWidth(), getHeight());
    }

    validateChildren(g);
  }

  void addLine(LineModel lineModel, FunctionFigure figure) {
    add(figure);
    this.map.put(lineModel, figure);
    invalidate();
  }

  void removeLine(LineModel lineModel) {
    final FunctionFigure figure = this.map.get(lineModel);
    remove(figure);
    this.map.remove(lineModel);
    invalidate();
  }

}
