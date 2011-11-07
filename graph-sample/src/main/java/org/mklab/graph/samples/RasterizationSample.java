/**
 * 
 */
package org.mklab.graph.samples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.mklab.abgr.Color;
import org.mklab.graph.function.ContinuousFunction2D;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.model.GraphModel;
import org.mklab.graph.g2d.model.LineModel;
import org.mklab.graph.swing.FigureRasterizer;


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
    final FigureRasterizer r = new FigureRasterizer();

    final GraphFigure graph = new GraphFigure();
    graph.setWidth(300);
    graph.setHeight(300);
    graph.getModel().setTitle("Sample GraphFigure");
    graph.getModel().setXAxisName("Name of X Axis");
    graph.getModel().setYAxisName("Name of Y Axis");
    graph.setScope(0, 10, -1, 1);

    final GraphModel graphModel = graph.getModel();
    final LineModel lineModel = new LineModel("y = sin(x)", Color.RED, new ContinuousFunction2D() {

      @Override
      public double evalY(double x) {
        return Math.sin(x);
      }
    });
    graphModel.addLineModel(lineModel);

    final BufferedImage image = r.rasterize(graph);
    try {
      ImageIO.write(image, "png", new File("target/test.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new RuntimeException(e);
    }
  }

}
