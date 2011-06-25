/**
 * 
 */
package org.mklab.ishikura.graph;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.mklab.ishikura.graph.g2d.LabeledCoordinateSpaceFigure;
import org.mklab.ishikura.graph.g2d.Scope;
import org.mklab.ishikura.graph.swing.FigureRasterizer;


/**
 * 適当なグラフをつくって画像にしてみるサンプルです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class RasterizationSample {

  /**
   * メインメソッドです。
   * 
   * @param args コマンドライン引数
   */
  @SuppressWarnings("nls")
  public static void main(final String[] args) {
    FigureRasterizer r = new FigureRasterizer();
    final LabeledCoordinateSpaceFigure coordinateSpace = new LabeledCoordinateSpaceFigure();

    coordinateSpace.setWidth(300);
    coordinateSpace.setHeight(300);
    coordinateSpace.setTitle("Sample GraphFigure");
    coordinateSpace.setNameOfX("Name of X Axis");
    coordinateSpace.setNameOfY("Name of Y Axis");
    coordinateSpace.setScope(new Scope(0, 300, 0, 300));

    final BufferedImage image = r.rasterize(coordinateSpace);
    try {
      ImageIO.write(image, "png", new File("target/test.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new RuntimeException(e);
    }
  }

}
