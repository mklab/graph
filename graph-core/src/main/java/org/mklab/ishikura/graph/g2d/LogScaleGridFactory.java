/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * ログスケールのグラフのグリッドを生成する{@link GridFactory}です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public class LogScaleGridFactory implements GridFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Grid create(double start, double end) {
    if (start > end) throw new IllegalArgumentException();
    if (start <= 0) throw new IllegalArgumentException("Log scale not support zero or negative value."); //$NON-NLS-1$

    final double logStart = Math.log10(start);
    int n = (int)Math.ceil(logStart);
    Grid grid = new Grid();
    if (Math.pow(10, n) > start) n--;
    while (true) {
      final double g = Math.pow(10, n);
      grid.add(g);
      n++;
      if (g >= end) break;
    }
    return grid;
  }

}
