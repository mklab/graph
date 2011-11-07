/**
 * 
 */
package org.mklab.graph.figure;

import java.util.List;


/**
 * 複数の図のコンテナを表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public interface ContainerFigure extends Figure {

  /**
   * 図の追加を行います。
   * 
   * @param figure 図
   */
  void add(Figure figure);

  /**
   * この図のサイズを、属しているすべての子のサイズに合わせます。
   */
  void pack();

  /**
   * 与えられた座標に存在する図を取得します。
   * 
   * @param x x座標
   * @param y y座標
   * @return 図
   */
  List<Figure> getFiguresAt(int x, int y);

}