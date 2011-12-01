package org.mklab.graph.control;

import org.mklab.graph.function.ListFunction2D;
import org.mklab.graph.function.realtime.RealtimeFunction;
import org.mklab.nfc.matrix.DoubleMatrix;


/**
 * @author Yuhi Ishikura
 */
public abstract class BodeFunction extends ListFunction2D implements RealtimeFunction, Bode.Callback {

  private CanvasUpdater canvasUpdater;
  private boolean computing = false;
  private int inputIndex;
  private Bode bode;

  /**
   * {@link BodeFunction}オブジェクトを構築します。
   * 
   * @param bode ボード線図の計算に用いるオブジェクト
   * @param inputIndex 入力信号のインデックス(0~)
   */
  public BodeFunction(Bode bode, int inputIndex) {
    this.bode = bode;
    this.inputIndex = inputIndex;
  }

  /**
   * ボード線図の再計算を行います。
   * 
   * @param freqs 計算する周波数
   */
  public final void compute(double[] freqs) {
    this.computing = true;
    this.bode.compute(freqs, new Bode.Callback() {

      @SuppressWarnings({"unqualified-field-access", "synthetic-access"})
      @Override
      public void computed(@SuppressWarnings("hiding") DoubleMatrix freqs, DoubleMatrix magnitudes, DoubleMatrix phases) {
        computing = false;
        clearPoints();
        BodeFunction.this.computed(freqs, magnitudes, phases);
        canvasUpdater.requestRedraw(true);
      }

      @Override
      public int getInputIndex() {
        return BodeFunction.this.getInputIndex();
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getInputIndex() {
    return this.inputIndex;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isComputing() {
    return this.computing;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setCanvasUpdater(CanvasUpdater canvasUpdater) {
    this.canvasUpdater = canvasUpdater;
  }

}
