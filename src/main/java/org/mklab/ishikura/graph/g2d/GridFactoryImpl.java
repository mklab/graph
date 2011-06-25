/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
class GridFactoryImpl implements GridFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public Grid create(double start, double end) {
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

}
