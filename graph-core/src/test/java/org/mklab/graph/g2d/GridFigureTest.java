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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mklab.abgr.Graphics;
import org.mklab.abgr.NullGraphics;
import org.mklab.graph.figure.ContainerFigureImpl;
import org.mklab.graph.g2d.GridFigure;
import org.mklab.graph.g2d.Scope;


/**
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class GridFigureTest {

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.GridFigure#layout(org.mklab.abgr.Graphics)}
   * .
   */
  @Test
  public void testLayout() {
    final GridFigure figure = new GridFigure();
    final Graphics g = new NullGraphics();

    boolean thrown = false;
    try {
      figure.layout(g);
    } catch (IllegalStateException ex) {
      thrown = true;
    }
    assertTrue(thrown);

    figure.setScope(new Scope(0, 300, 0, 300));
    figure.layout(g);
    assertNotNull(figure.getGridX());
    assertNotNull(figure.getGridY());

    assertEquals(figure.getWidth(), figure.measureX.getViewSize());
    assertEquals(figure.getHeight(), figure.measureY.getViewSize());
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.GridFigure#modelToViewX(double)} .
   */
  @Test
  public void testModelToView() {
    final GridFigure figure = new GridFigure();
    final Graphics g = new NullGraphics();
    figure.setWidth(300);
    figure.setHeight(300);
    figure.setScope(new Scope(0, 300, 0, 300));
    figure.layout(g);

    assertEquals(299, figure.modelToViewX(299.999));
    assertEquals(-1, figure.modelToViewX(300));
    assertEquals(0, figure.modelToViewX(0));
    assertEquals(0, figure.modelToViewY(299.999));
    assertEquals(299, figure.modelToViewY(0));
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.GridFigure#modelToViewX(double)} .
   */
  @Test
  public void testModelToViewFigure() {
    final GridFigure gridFigure = new GridFigure();
    final Graphics g = new NullGraphics();
    final ContainerFigureImpl parent = new ContainerFigureImpl();

    gridFigure.setX(10);
    gridFigure.setY(10);
    gridFigure.setWidth(300);
    gridFigure.setHeight(300);

    gridFigure.setScope(new Scope(0, 300, 0, 300));
    parent.add(gridFigure);
    parent.draw(g);

    assertEquals(0, gridFigure.modelToViewY(parent, 299.999));
    assertEquals(-1, gridFigure.modelToViewY(parent, 300));
    assertEquals(299, gridFigure.modelToViewY(parent, 0));
  }

  /**
   * Test method for
   * {@link org.mklab.graph.g2d.GridFigure#viewToModelX(int)} .
   */
  @Test
  public void testViewToModel() {
    final GridFigure figure = new GridFigure();
    final Graphics g = new NullGraphics();
    figure.setWidth(300);
    figure.setHeight(300);
    figure.setScope(new Scope(0, 300, 0, 300));
    figure.layout(g);

    assertEquals(300, figure.viewToModelX(299), 0);
    assertEquals(0, figure.viewToModelX(0), 0);
    assertEquals(0, figure.viewToModelY(299), 0);
    assertEquals(300, figure.viewToModelY(0), 0);
  }

}
