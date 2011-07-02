/**
 * 
 */
package org.mklab.ishikura.graph.function;

import java.util.Iterator;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public final class Functions {

  /**
   * startからendまでの値のイテレータを生成します。
   * 
   * @param start 最初の値
   * @param end 最後の値
   * @param divisionCount 分割数
   * @return startからendまでの値のイテレータ
   */
  public static Iterator<Double> createDoubleIterator(final double start, final double end, final int divisionCount) {
    return createDoubleIterator(start, true, end, true, divisionCount);
  }

  /**
   * startからendまでの値のイテレータを生成します。
   * 
   * @param start 最初の値
   * @param containsStart 最初の値を含めるかどうか
   * @param end 最後の値
   * @param containsEnd 最後の値を含めるかどうか
   * @param divisionCount 分割数
   * @return startからendまでの値のイテレータ
   */
  public static Iterator<Double> createDoubleIterator(final double start, final boolean containsStart, final double end, final boolean containsEnd, final int divisionCount) {
    if (start > end) throw new IllegalArgumentException();
    double length = end - start;
    final int startIndex;
    if (containsStart == false) {
      length--;
      startIndex = 1;
    } else {
      startIndex = 0;
    }
    if (containsEnd == false) length--;

    final double dx = length / divisionCount;
    return new Iterator<Double>() {

      int index = startIndex;

      @Override
      public boolean hasNext() {
        if (containsEnd) return this.index <= divisionCount;
        return this.index < divisionCount;
      }

      @Override
      public Double next() {
        return Double.valueOf(start + dx * this.index++);
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

    };
  }
}
