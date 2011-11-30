/*
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

import org.mklab.graph.figure.Canvas;
import org.mklab.graph.function.realtime.RealtimeFunction.CanvasUpdater;

import java.util.concurrent.TimeUnit;


/**
 * 短い間隔の再描画を省略することで効率化を図る {@link CanvasUpdater}です。
 * 
 * @author Yuhi Ishikura
 */
public class EffectiveCanvasUpdater implements RealtimeFunction.CanvasUpdater {

  private Canvas canvas;
  private long lastUpdate;
  private boolean drawn = false;

  /**
   * {@link EffectiveCanvasUpdater}オブジェクトを構築します。
   * 
   * @param canvas キャンバス
   */
  public EffectiveCanvasUpdater(Canvas canvas) {
    this.canvas = canvas;
  }

  /**
   * 描画要求後、描画されたかどうかを調べます。
   * 
   * @return 描画要求後再描画されたかどうか
   */
  public boolean isDrawn() {
    return this.drawn;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void requestRedraw(boolean force) {
    if (force) {
      redrawNow();
      return;
    }

    final long current = System.currentTimeMillis();
    if (current - this.lastUpdate < TimeUnit.SECONDS.toMillis(1)) {
      return;
    }
    redrawNow();
  }

  private void redrawNow() {
    this.canvas.redrawCanvas();
    this.drawn = true;
    this.lastUpdate = System.currentTimeMillis();
  }

}
