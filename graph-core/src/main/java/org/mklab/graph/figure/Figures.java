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
package org.mklab.graph.figure;

/**
 * 図関連のユーティリティを提供するクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public final class Figures {

  /**
   * 図の絶対位置を取得します。
   * 
   * @param figure 図
   * @return 図の絶対位置
   */
  public static Point getAbsoluteLocation(Figure figure) {
    if (figure == null) throw new NullPointerException();

    int x = 0;
    int y = 0;
    Figure f = figure;
    do {
      x += f.getX();
      y += f.getY();
      f = f.getParent();
    } while (f != null);

    return new Point(x, y);
  }

  /**
   * from上の座標pをto上の座標に変換します。
   * 
   * @param from 変換元の座標系
   * @param to 変換先の座標系
   * @param p 座標
   * @return 変換して得られたto上の座標
   */
  public static Point convertPoint(Figure from, Figure to, Point p) {
    if (from == null || to == null || p == null) throw new NullPointerException();
    if (from == to) return new Point(p.x, p.y);

    final Point fromPoint = getAbsoluteLocation(from);
    final Point toPoint = getAbsoluteLocation(to);

    return new Point(p.x + fromPoint.x - toPoint.x, p.y + fromPoint.y - toPoint.y);
  }

}
