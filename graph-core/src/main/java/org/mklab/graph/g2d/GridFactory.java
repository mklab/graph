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
package org.mklab.graph.g2d;

/**
 * グリッドを生成するファクトリです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/06/25
 */
public interface GridFactory {

  /**
   * グリッドを生成します。
   * <p>
   * グリッドの開始は、区間の始まり以前であり、
   * グリッドの終了は、区間の終了以降となります。
   * 
   * @param start 区間の始まり
   * @param end 区間の終わり
   * @return グリッド
   * @throws IllegalArgumentException 不正な区間が指定された場合
   */
  Grid create(double start, double end);

}
