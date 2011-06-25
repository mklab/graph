/**
 * 
 */
package org.mklab.ishikura.graph.swing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.mklab.ishikura.graph.figure.Figure;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class FigureRasterizer {

  /**
   * 図を画像としてラスタライズします。
   * 
   * @param figure 図
   * @return 画像
   */
  public BufferedImage rasterize(Figure figure) {
    if (figure.getWidth() == 0 || figure.getHeight() == 0) throw new IllegalArgumentException("invalid figure size"); //$NON-NLS-1$

    final BufferedImage image = new BufferedImage(figure.getWidth(), figure.getHeight(), BufferedImage.TYPE_INT_ARGB);
    final Graphics g = new AwtGraphics((Graphics2D)image.getGraphics());
    figure.draw(g);

    return image;
  }

}
