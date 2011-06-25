/*
 * Created on 2010/10/18
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 単系列のグリッドを表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class Grid implements Iterable<Double> {

  private List<Double> graduations;

  /**
   * 与えられた区間を分割するグリッドの生成を行います。
   * <p>
   * TODO 別クラスへの移動
   * 
   * @param start 開始位置
   * @param end 終了位置
   * @return グリッド
   */
  static Grid create(double start, double end) {
    if (start > end) throw new IllegalArgumentException(start + " > " + end); //$NON-NLS-1$

    int interval = (int)(end - start + 0.5);
    interval = interval / 5;
    interval = interval / 10 * 10;
    if (interval == 0) interval = 1;

    final Grid grid = new Grid();

    int n = (int)start / interval * interval;
    if (n < start) n += interval;
    while (n <= end) {
      grid.add(n);
      n += interval;
    }

    return grid;
  }

  /**
   * 新しく生成された<code>CoordinateSpace2D.Grid</code>オブジェクトを初期化します。
   */
  Grid() {
    this.graduations = new ArrayList<Double>();
  }

  /**
   * 目盛を追加します。
   * 
   * @param graduation 目盛
   */
  void add(double graduation) {
    this.graduations.add(Double.valueOf(graduation));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<Double> iterator() {
    return this.graduations.iterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Grid " + this.graduations; //$NON-NLS-1$
  }
}