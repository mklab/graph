/**
 * 
 */
package org.mklab.ishikura.graph.g2d.plotter;

import java.util.ArrayList;
import java.util.List;

import org.mklab.ishikura.graph.g2d.GridFigure;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 折れ線描画を行うクラスです。
 * <p>
 * 描画の際に、グラフ外の点について考慮し、必要最低限の描画を試みます。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/07
 */
class PolylineRenderer {

  List<int[]> pointsToDraw = new ArrayList<int[]>();
  GridFigure grid;

  int[] pointArray;
  int size;
  boolean firstAppend;

  boolean previousIsOutOfGridBound;
  int previousX;
  int previousY;

  PolylineRenderer(GridFigure grid) {
    if (grid == null) throw new NullPointerException();
    this.grid = grid;

    initialize();
  }

  private void initialize() {
    this.size = 0;
    this.pointArray = null;
    this.previousIsOutOfGridBound = false;
    this.firstAppend = true;
  }

  /**
   * 折れ線の点を追加します。
   * <p>
   * グラフ外の点については基本的に追加を行ないませんが、
   * <ul>
   * <li>ひとつ前の点がグラフ外の点で、今回がグラフ内の点の場合</li>
   * <li>ひとつ前の点がグラフ内の点で、今回がグラフ外の点の場合</li>
   * </ul>
   * には、追加を行ないます。
   * 
   * @param x x座標
   * @param y y座標
   */
  void appendPoint(int x, int y) {
    // 折れ線記憶域の準備
    readyPointArray();
    if (2 * this.size >= this.pointArray.length) {
      extentPointArraySize();
    }
    // 点の追加
    final boolean currentIsOutOfGridBound = isOutOfGridBound(x, y);
    final boolean outToIn = this.previousIsOutOfGridBound && currentIsOutOfGridBound == false;
    final boolean inToOut = this.previousIsOutOfGridBound == false && currentIsOutOfGridBound;

    if (this.firstAppend == false && outToIn) {
      startNewPolyline();
      appendPointImpl(this.previousX, this.previousY);
    }
    if (currentIsOutOfGridBound == false) appendPointImpl(x, y);
    if (this.firstAppend == false && inToOut) appendPointImpl(x, y);

    // 次回の追加のために保存
    this.previousIsOutOfGridBound = currentIsOutOfGridBound;
    this.previousX = x;
    this.previousY = y;
    this.firstAppend = false;
  }

  private void appendPointImpl(int x, int y) {
    this.pointArray[2 * this.size] = x;
    this.pointArray[2 * this.size + 1] = y;
    this.size++;
  }

  private boolean isOutOfGridBound(int x, int y) {
    if (x < 0) return true;
    if (y < 0) return true;
    if (x >= this.grid.getWidth()) return true;
    if (y >= this.grid.getHeight()) return true;
    return false;
  }

  private void readyPointArray() {
    if (this.grid.getWidth() == 0) throw new IllegalStateException();
    if (this.pointArray != null) return;
    this.pointArray = new int[this.grid.getWidth()];
  }

  private void extentPointArraySize() {
    if (this.pointArray == null) throw new IllegalStateException();

    int[] old = this.pointArray;
    this.pointArray = new int[(int)(Math.ceil(old.length * 1.5))];
    System.arraycopy(old, 0, this.pointArray, 0, 2 * this.size);
  }

  void startNewPolyline() {
    flush();
    readyPointArray();
  }

  /**
   * 現在の折れ線を保存し、初期状態にします。
   */
  private void flush() {
    if (this.size > 0) this.pointsToDraw.add(createPointArray());
    initialize();
  }

  void draw(Graphics g) {
    if (this.size > 0) flush();
    for (final int[] points : this.pointsToDraw) {
      g.drawPolyline(points);
    }
  }

  /**
   * 現在の折れ線を配列x1,y1,x2,y2...で取得します。
   * <p>
   * テストのために公開しています。
   * 
   * @return 現在の折れ線を表す配列
   */
  int[] createPointArray() {
    if (this.pointArray.length == 2 * this.size) return this.pointArray;
    int[] i = new int[2 * this.size];
    System.arraycopy(this.pointArray, 0, i, 0, 2 * this.size);
    return i;
  }

}
