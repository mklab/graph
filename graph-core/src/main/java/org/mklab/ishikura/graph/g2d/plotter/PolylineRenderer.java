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
public class PolylineRenderer {

  /** グリッド外に点が飛び出た場合に、値をこの値に押さえつけます。 */
  private int saturator = 1000;
  /** 飽和を有効にするかどうかを保持します。 */
  private boolean saturationEnabled = true;

  /** 複数の折れ線の構成点のリストです。 */
  private List<int[]> pointsToDraw = new ArrayList<int[]>();
  /** 描画対象のグリッドです。 */
  private GridFigure grid;

  /** 現在記述中の折れ線の構成点(x1,y1,x2,y2,...)の保存領域です。 */
  private int[] pointArray;
  /**
   * {@link #pointArray}中の構成点の数です。{@link #pointArray}はx,y,x,yで保存されているため、
   * {@link #pointArray}の配列のサイズにするには2倍する必要があります。
   */
  private int size;
  /** 現在記述中の折れ線の一回目の {@link #appendPoint(int, int)}の場合trueになります。 */
  private boolean firstAppend;

  /** 前回グリッドの外の点だったかどうかを保持します。 */
  private boolean previousIsOutOfGridBound;
  /** 前回のx座標です。 */
  private int previousX;
  /** 前回のy座標です。 */
  private int previousY;

  /**
   * {@link PolylineRenderer}オブジェクトを構築します。
   * 
   * @param grid 描画対象のグリッド
   */
  public PolylineRenderer(GridFigure grid) {
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
   * グリッド外に点が飛び出た場合に、値をこの値に押さえつけます。
   * <p>
   * 例えば、この値が100で、グリッドが100x100の場合に、 <code>
   * (x,y)=(-1000,1000)</code>が指定された場合には、<code>(-100,100)</code>に、
   * <code>(1000,1000)</code>が指定された場合には、<code>(200,200)</code>なります。
   * 
   * @param saturator 飽和の値
   */
  public void setSaturator(int saturator) {
    if (saturator < 0) throw new IllegalArgumentException();
    this.saturator = saturator;
  }

  /**
   * 飽和の有効/無効を設定します。
   * 
   * @param saturationEnabled 有効にするならば{@code true}、無効にするならば{@code false}
   */
  public void setSaturationEnabled(boolean saturationEnabled) {
    this.saturationEnabled = saturationEnabled;
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
  public void appendPoint(int x, int y) {
    readyPointArray();

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
    if (2 * this.size + 1 >= this.pointArray.length) {
      extentPointArraySize();
    }
    this.pointArray[2 * this.size] = filterBySaturator(x, this.grid.getWidth());
    this.pointArray[2 * this.size + 1] = filterBySaturator(y, this.grid.getHeight());
    this.size++;
  }

  int filterBySaturator(final int value, final int width) {
    if (this.saturationEnabled == false) return value;
    if (value < -this.saturator) return -this.saturator;
    if (value > width + this.saturator) return width + this.saturator;
    return value;
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

  /**
   * 折れ線を描画します。
   * 
   * @param g 描画対象
   */
  public void draw(Graphics g) {
    if (this.size > 0) flush();
    for (final int[] points : this.pointsToDraw) {
      g.drawPolyline(points);
    }
  }

}
