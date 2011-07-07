/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * 一般的なグラフに用いるグリッドを生成するクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
class StandardGridFactory implements GridFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Grid create(double start, double end) {
    if (start >= end) throw new IllegalArgumentException(start + " >= " + end); //$NON-NLS-1$

    double interval = fix(getGridInterval(end - start));
    final Grid grid = new Grid();

    double n = (int)(start / interval) * interval;
    n = fix(n);

    if (n > start) n = fix(n - interval);
    while (n < fix(end + interval)) {
      grid.add(n);
      n = fix(n + interval);
    }

    return grid;
  }

  /**
   * 精度による問題を修正します。
   * <p>
   * ex) 999.9999999999...=> 1000
   * 
   * @param d 値
   * @return 修正した値
   */
  // FIXME 近似の仕方が分からない
  @SuppressWarnings("boxing")
  private double fix(double d) {
    return Double.parseDouble(String.format("%f", d)); //$NON-NLS-1$
  }

  /**
   * 与えられた値を0~29の値にするために必要な乗数を計算します。
   * 
   * @param l 値
   * @return 0~29の値にするために掛ける必要がある値
   */
  double getMultiplierForMakingNumberBetween0_29(double l) {
    double length = l;
    double multiplier = 1;
    if (length < 0) {
      length *= -1;
      multiplier *= -1;
    }
    while (length >= 30) {
      length /= 10;
      multiplier /= 10;
    }
    while (length < 3) {
      length *= 10;
      multiplier *= 10;
    }
    return multiplier;
  }

  double getGridInterval(double d) {
    double n = d;

    final double mul = getMultiplierForMakingNumberBetween0_29(d);
    n *= mul;
    double gridInterval = mapGridInterval(n);
    gridInterval /= mul;

    return fix(gridInterval);
  }

  /**
   * グリッド間隔を取得します。
   * 
   * @param d 3~29までの値
   * @return グリッド間隔
   */
  double mapGridInterval(double d) {
    if (d < 3) throw new IllegalArgumentException(d + " < " + 3); //$NON-NLS-1$
    if (d >= 30) throw new IllegalArgumentException(d + " >= " + 30); //$NON-NLS-1$

    if (d < 6) return 1;
    if (d < 12) return 2;
    if (d < 15) return 4;
    if (d < 30) return 5;

    throw new IllegalArgumentException();
  }

}
