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

import java.text.MessageFormat;


/**
 * 座標を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/22
 */
public class Point {

  /** x座標です。 */
  public int x;
  /** y座標です。 */
  public int y;

  /**
   * {@link Point}オブジェクトを構築します。
   * 
   * @param x x座標
   * @param y y座標
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.x;
    result = prime * result + this.y;
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (this.getClass() != obj.getClass()) return false;
    Point other = (Point)obj;
    if (this.x != other.x) return false;
    if (this.y != other.y) return false;
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return MessageFormat.format("Point [x={0}, y={1}]", this.x, this.y); //$NON-NLS-1$
  }

}
