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

import java.util.Iterator;

import org.mklab.graph.function.Function2D;
import org.mklab.graph.function.Functions;
import org.mklab.graph.function.ParameterFunction2D;
import org.mklab.graph.function.PolarFunction2D;


/**
 * @author ishikura
 * @version $Revision$, 2011/07/02
 */
public interface SampleFunctions {

  /** 内サイクロイドです。 */
  Function2D HYPOCYCLOID = new ParameterFunction2D() {

    @Override
    public Iterator<Double> provideParameter() {
      return Functions.createDoubleIterator(0, 2 * Math.PI, 1000);
    }

    @Override
    public double evalY(double t) {
      return 2 * Math.sin(t) - Math.sin(2 * t);
    }

    @Override
    public double evalX(double t) {
      return 2 * Math.cos(t) + Math.cos(2 * t);
    }
  };

  /** 三葉線です。 */
  Function2D SANYO = new PolarFunction2D() {

    @Override
    public double evalR(double radian) {
      return 3 * Math.sin(3 * radian);
    }
  };

  /** 四葉線です。 */
  Function2D SHIYO = new PolarFunction2D() {

    @Override
    public double evalR(double radian) {
      return 3 * Math.sin(2 * radian);
    }
  };

  /**
   * レムニスケートです。
   * 
   * @deprecated r^2=..が本来の形なので不完全です。
   */
  @Deprecated
  Function2D LEMNISCATE = new PolarFunction2D() {

    @Override
    public double evalR(double radian) {
      return Math.sqrt(8 * Math.cos(2 * radian));
    }

  };

  /** 蝸牛線です。 */
  Function2D LIMACON = new PolarFunction2D() {

    static final double a = 2;
    static final double b = 1;

    @Override
    public double evalR(double radian) {
      return a * Math.cos(radian) + b;
    }

  };

  /** 螺獅線です。 */
  Function2D CONCHOID = new PolarFunction2D() {

    static final double a = 1;
    static final double b = 2;

    @Override
    public double evalR(double radian) {
      return a / Math.cos(radian) + b;
    }

  };
  /** 螺旋です。 */
  Function2D LOGARITHMIC_SPIRAL = new PolarFunction2D() {

    static final double a = 0.5;
    static final double b = 1;

    @Override
    public double evalR(double radian) {
      return b * Math.exp(a * radian);
    }

    @Override
    public Iterator<Double> provideTheta() {
      return Functions.createDoubleIterator(0, 10 * Math.PI, 1000);
    }

  };

}
