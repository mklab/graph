package org.mklab.ishikura.graph.samples;

import java.util.Iterator;

import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.function.Functions;
import org.mklab.ishikura.graph.function.ParameterFunction2D;
import org.mklab.ishikura.graph.function.PolarFunction2D;


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
  Function2D LOG_ARITHMIC_SPIRAL = new PolarFunction2D() {

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
