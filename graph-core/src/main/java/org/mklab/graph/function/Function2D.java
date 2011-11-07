/*
 * Created on 2010/10/12
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
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
