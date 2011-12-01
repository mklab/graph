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

import org.mklab.graph.figure.Canvas;
import org.mklab.graph.figure.CanvasListener;


/**
 * @author Yuhi Ishikura
 */
public class CanvasMock implements Canvas {

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCanvasWidth() {
    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCanvasHeight() {
    return 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redrawCanvas() {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addCanvasListener(@SuppressWarnings("unused") CanvasListener canvasListener) {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeCanvasListener(@SuppressWarnings("unused") CanvasListener canvasListener) {
    // do nothing
  }

}
