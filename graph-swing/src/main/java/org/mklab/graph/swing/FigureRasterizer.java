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
package org.mklab.graph.swing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.mklab.abgr.Graphics;
import org.mklab.abgr.awt.AwtGraphics;
import org.mklab.graph.figure.Figure;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class FigureRasterizer {

  /**
   * 図を画像としてラスタライズします。
   * 
   * @param figure 図
   * @return 画像
   */
  public BufferedImage rasterize(Figure figure) {
    if (figure.getWidth() == 0 || figure.getHeight() == 0) throw new IllegalArgumentException("invalid figure size"); //$NON-NLS-1$

    final BufferedImage image = new BufferedImage(figure.getWidth(), figure.getHeight(), BufferedImage.TYPE_INT_ARGB);
    final Graphics g = new AwtGraphics((Graphics2D)image.getGraphics());
    figure.draw(g);

    return image;
  }

}
