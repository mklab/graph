/**
 * 
 */
package org.mklab.ishikura.graph;

import org.mklab.ishikura.graph.g2d.GraphFigure;


/**
 * グラフの表示を行うインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public interface GraphViewer {

  /**
   * 与えられたグラフを表示します。
   * 
   * @param graph グラフ
   */
  void show(GraphFigure graph);

}
