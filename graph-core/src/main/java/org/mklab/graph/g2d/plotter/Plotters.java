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
package org.mklab.graph.g2d.plotter;

/**
 * {@link Plotter}の実装を補助するユーティリティクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/06
 */
final class Plotters {

  private Plotters() {
    // do nothing
  }

  static boolean isValidNumber(double d) {
    return Double.isInfinite(d) == false && Double.isNaN(d) == false;
  }

}
