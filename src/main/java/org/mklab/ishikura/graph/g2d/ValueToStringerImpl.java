/**
 * 
 */
package org.mklab.ishikura.graph.g2d;

/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/26
 */
class ValueToStringerImpl implements ValueToStringer {

  /**
   * {@inheritDoc}
   */
  @Override
  public String valueToString(double value) {
    if (value == ((int)value)) return String.valueOf((int)value);
    return Double.toString(value);
  }

}
