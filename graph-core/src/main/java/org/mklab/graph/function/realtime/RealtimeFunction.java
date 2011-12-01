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
package org.mklab.graph.function.realtime;

/**
 * リアルタイムに描画を行う関数を表すインターフェースです。
 * <p>
 * 例えば、計算に時間がかかり、計算中にも描画を行わせたい場合や、リアルタイムな入力を描画したい場合に利用します。
 * 
 * @author Yuhi Ishikura
 */
public interface RealtimeFunction {

  /**
   * 計算中であるか否かを調べます。
   * 
   * @return 計算中であるかどうか
   */
  boolean isComputing();

  /**
   * キャンバスの更新を行わせるオブジェクトを設定します。
   * 
   * @param canvasUpdater キャンバスの更新を行わせるオブジェクト
   */
  void setCanvasUpdater(CanvasUpdater canvasUpdater);

  /**
   * キャンバスの更新を行うインターフェースです。
   * 
   * @author Yuhi Ishikura
   */
  public static interface CanvasUpdater {

    /**
     * キャンバスの再描画を要求します。
     * 
     * @param force trueであれば出来る限り強制的に再描画を行わせます。falseの場合は再描画するかどうかは描画する側で決定します。
     */
    void requestRedraw(boolean force);

  }

}
