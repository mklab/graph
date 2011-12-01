/*
 * Copyright (C) 2011 Koga Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mklab.graph.function;

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
