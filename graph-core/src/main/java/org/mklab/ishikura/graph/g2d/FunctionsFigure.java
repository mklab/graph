/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

import org.mklab.ishikura.graph.figure.ContainerFigureImpl;
import org.mklab.ishikura.graph.figure.Figure;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 複数の関数を束ねる図です。
 * <p>
 * すべての子の図を、この図と同じサイズにします。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
final class FunctionsFigure extends ContainerFigureImpl {

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

}
