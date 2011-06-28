/**
 * 
 */
package org.mklab.ishikura.graph.figure;

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
