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

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.mklab.graph.GraphViewer;
import org.mklab.graph.g2d.GraphFigure;


/**
 * Swingによるグラフビューアの実装です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public class SwingGraphViewer implements GraphViewer {

  /**
   * {@inheritDoc}
   */
  @Override
  public void show(final GraphFigure graph) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          final JFrame frame = new JFrame();
          frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

          final GraphComponent graphComponent = new GraphComponent();
          graph.setCanvas(graphComponent);
          graphComponent.setGraph(graph);

          frame.add(graphComponent);
          graphComponent.setPreferredSize(new Dimension(graph.getWidth(), graph.getHeight()));
          frame.pack();

          frame.setVisible(true);
        }

      });
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

}
