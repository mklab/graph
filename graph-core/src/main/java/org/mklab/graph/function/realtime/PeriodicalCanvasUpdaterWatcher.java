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

import org.mklab.graph.function.realtime.RealtimeFunction.CanvasUpdater;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


/**
 * {@link EffectiveCanvasUpdater}
 * により効率化を行うと、最後の描画時にその効率化が有効となった場合に、最後まで描画されずに終わってしまうので、それを補助するためのクラスです。
 * <p>
 * 周期的に有効なすべての {@link EffectiveCanvasUpdater}が未描画状態ではないか調べ、未描画状態であれば再描画させます。
 * 
 * @author Yuhi Ishikura
 */
public class PeriodicalCanvasUpdaterWatcher {

  private static final long TIMEOUT = TimeUnit.SECONDS.toMillis(10);
  private Map<EffectiveCanvasUpdater, Long> activateTimes;
  private Timer timer;

  /**
   * {@link PeriodicalCanvasUpdaterWatcher}オブジェクトを構築します。
   */
  public PeriodicalCanvasUpdaterWatcher() {
    this.timer = new Timer(true);
    this.activateTimes = new HashMap<EffectiveCanvasUpdater, Long>();
    this.timer.schedule(new TimerTask() {

      @Override
      public void run() {
        update();
      }
    }, TimeUnit.SECONDS.toMillis(3));
  }

  synchronized void update() {
    final long current = System.currentTimeMillis();
    Set<CanvasUpdater> willRemove = null;
    for (EffectiveCanvasUpdater updater : this.activateTimes.keySet()) {
      Long activateTime = this.activateTimes.get(updater);
      if (activateTime == null || current - activateTime.longValue() > TIMEOUT) {
        if (willRemove == null) {
          willRemove = new HashSet<RealtimeFunction.CanvasUpdater>();
        }
        willRemove.add(updater);
        continue;
      }

      if (updater.isDrawn() == false) {
        updater.requestRedraw(false);
      }
    }

    if (willRemove != null) {
      for (CanvasUpdater c : willRemove) {
        this.activateTimes.remove(c);
      }
    }
  }

  /**
   * {@link CanvasUpdater}がアクティブであることを知らせます。
   * 
   * @param updater キャンバス更新オブジェクト
   */
  public synchronized void activate(EffectiveCanvasUpdater updater) {
    this.activateTimes.put(updater, Long.valueOf(System.currentTimeMillis()));
  }

}