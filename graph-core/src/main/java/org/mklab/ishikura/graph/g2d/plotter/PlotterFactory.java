/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import org.mklab.ishikura.graph.function.Function2D;


/**
 * {@link Plotter}インスタンスを生成します。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/01
 */
public interface PlotterFactory {

  /**
   * 与えられた関数を描画する{@link Plotter}を生成します。
   * 
   * @param function 描画する関数
   * @return {@link Plotter}インスタンス
   */
  Plotter create(Function2D function);

}
