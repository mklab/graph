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

/**
 * 二次元の関数を表すインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/12
 */
public interface Function2D {

  /**
   * 与えられた座標が関数上に存在するかどうか調べます。
   * <p>
   * 座標(x1,y1)が関数上に存在する場合、 <code>
   * <pre>
   * x1 - delta &lt;= x &lt;= x1 + delta
   * y1 - delta &lt;= y &lt;= y1 + delta
   * </pre>
   * </code> の場合に含まれていると見なされます。
   * 
   * @param x x座標
   * @param y y座標
   * @param delta 座標からのズレの許容量。
   * @return 含まれているかどうか
   */
  boolean contains(double x, double y, double delta);

}
