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
package org.mklab.graph.samples;

import org.mklab.abgr.Color;
import org.mklab.graph.control.Bode;
import org.mklab.graph.control.BodePlotter;
import org.mklab.graph.control.GainFunction;
import org.mklab.graph.control.PhaseFunction;
import org.mklab.graph.g2d.GraphFigure;
import org.mklab.graph.g2d.model.GraphModel;
import org.mklab.graph.g2d.model.GridType;
import org.mklab.graph.g2d.model.LineModel;
import org.mklab.nfc.scalar.Polynomial;
import org.mklab.nfc.scalar.RationalPolynomial;
import org.mklab.tool.control.LinearSystemFactory;


/**
 * @author yuhi
 */
public class BodePlotSample extends GraphFigureSample {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphModel(GraphModel model) {
    model.setGridTypeX(GridType.LOG);
    final Bode bode = new Bode(LinearSystemFactory.createLinearSystem(new RationalPolynomial(1, new Polynomial(new double[] {1, 11, 6, 1}))));
    final GainFunction gainFunction = new GainFunction(bode, 0);
    final PhaseFunction phaseFunction = new PhaseFunction(bode, 0);
    final LineModel gainLine = new LineModel("gain", Color.RED, gainFunction, new BodePlotter(gainFunction));
    final LineModel phaseLine = new LineModel("phase", Color.GREEN, phaseFunction, new BodePlotter(phaseFunction));

    model.addLineModel(gainLine);
    model.addLineModel(phaseLine);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initGraphFigure(GraphFigure figure) {
    figure.setScope(1e-2, 1e0, -300, 50);
    figure.setSize(500, 500);
  }

  /**
   * メインメソッドです。
   * 
   * @param args 未使用
   */
  public static void main(String[] args) {
    new BodePlotSample().launch();
  }

}
