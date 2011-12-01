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

import java.util.ArrayList;
import java.util.List;


/**
 * {@link CanvasListener}を管理するクラスです。
 * 
 * @author Yuhi Ishikura
 */
public class CanvasListenerList {

  private List<CanvasListener> canvasListeners = new ArrayList<CanvasListener>();

  /**
   * リスナーを追加します。
   * 
   * @param l リスナー
   */
  public void add(CanvasListener l) {
    this.canvasListeners.add(l);
  }

  /**
   * リスナーを削除します。
   * 
   * @param l リスナー
   */
  public void remove(CanvasListener l) {
    this.canvasListeners.remove(l);
  }

  /**
   * キャンバスの大きさが変更されたとことを通知します。
   */
  public void fireCanvasSizeChanged() {
    for (CanvasListener l : this.canvasListeners) {
      l.canvasSizeChanged();
    }
  }

}
