/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import org.mklab.ishikura.graph.g2d.GridFigure;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 関数をグラフに描画するインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
public interface Plotter {

  /**
   * 関数を描画対象<code>g</code>に描画します。
   * 
   * @param g 描画対象
   * @param grid グリッド
   */
  void plot(Graphics g, GridFigure grid);

}
