/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public class LogScaleGridFactory implements GridFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Grid create(double start, double end) {
    final double logStart = Math.log10(start);
    final double logEnd = Math.log10(end);
    int n = (int)Math.ceil(logStart);
    Grid grid = new Grid();
    while (n <= logEnd) {
      grid.add(Math.pow(10, n));
      n++;
    }
    return grid;
  }

}
