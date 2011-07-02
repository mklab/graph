/*
 * Created on 2010/10/12
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.g2d;

import java.text.MessageFormat;


/**
 * 数学的な値の範囲を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/12
 */
public class Bound {

  /** 範囲の開始位置です。 */
  private double start;
  /** 範囲の終了位置です。 */
  private double end;

  /**
   * {@link Bound}オブジェクトを構築します。
   * 
   * @param start 範囲の開始位置
   * @param end 範囲の終了位置
   */
  public Bound(double start, double end) {
    if (start > end) throw new IllegalArgumentException();
    this.start = start;
    this.end = end;
  }

  /**
   * <code>base</code>を基準として区間を拡大(<code>ratio</code>倍)した新たな区間を生成します。
   * 
   * @param base 基準座標
   * @param ratio 拡大率
   * @return 拡大した新しい区間
   */
  public Bound scaledBound(double base, double ratio) {
    final double a = base - this.start;
    final double b = this.end - base;
    final double w = this.end - this.start;

    final double dStart = (w - w / ratio) * a / (a + b);
    final double dEnd = -(w - w / ratio) * b / (a + b);

    return new Bound(this.start + dStart, this.end + dEnd);
  }

  /**
   * 範囲の開始位置を取得します。
   * 
   * @return 範囲の開始位置
   */
  public double getStart() {
    return this.start;
  }

  /**
   * 範囲の終了位置を取得します。
   * 
   * @return 範囲の終了位置
   */
  public double getEnd() {
    return this.end;
  }

  /**
   * 範囲の幅を計算します。
   * 
   * @return 範囲の幅
   */
  public double getWidth() {
    return this.end - this.start;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return MessageFormat.format("Bound [start={0}, end={1}]", this.start, this.end); //$NON-NLS-1$
  }

}
