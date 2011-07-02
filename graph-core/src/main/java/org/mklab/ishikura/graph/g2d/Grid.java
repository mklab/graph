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
 * <p>
 * このクラスでは、グラフの目盛りを保持します。 目盛りの値は、モデルの座標であり、ビュー上での座標ではありません。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class Grid implements Iterable<Double> {

  private List<Double> graduations;

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