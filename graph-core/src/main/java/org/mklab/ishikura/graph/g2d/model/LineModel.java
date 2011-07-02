/**
 * 
 */
package org.mklab.ishikura.graph.g2d.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.mklab.ishikura.graph.function.Function2D;
import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.LineType;


/**
 * 単一の線のモデルを表すクラスです。
 * <p>
 * 変更可能なすべてのプロパティは監視可能です。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public class LineModel {

  /*
   * どの線がどの関数なのか判別できないのは困るため、
   * 線の名前、線の色については厳しくnullチェックしています。
   */

  /** 線幅のプロパティ名です。 */
  public static final String LINE_WIDTH_PROPERTY_NAME = "lineWidth"; //$NON-NLS-1$
  /** 線種プロパティ名です。 */
  public static final String LINE_TYPE_PROPERTY_NAME = "lineType"; //$NON-NLS-1$
  /** 線の色のプロパティ名です。 */
  public static final String LINE_COLOR_PROPERTY_NAME = "lineColor"; //$NON-NLS-1$
  /** ラベルのプロパティ名です。 */
  public static final String LABEL_PROPERTY_NAME = "label"; //$NON-NLS-1$

  /** 関数です。 */
  private Function2D function;
  /** 関数のラベルです。 */
  private String label;
  /** 線の描画色です。 */
  private Color lineColor;
  /** 線種です。 */
  private LineType lineType = LineType.DEFAULT;
  /** 線の太さです。 */
  private float lineWidth = 1f;
  /** {@link PropertyChangeListener}の管理に利用します。 */
  private PropertyChangeSupport propertyChangeSupport;

  /**
   * {@link LineModel}オブジェクトを構築します。
   * 
   * @param label ラベル
   * @param lineColor 線の色
   * @param function 関数
   */
  public LineModel(String label, Color lineColor, Function2D function) {
    if (function == null) throw new NullPointerException("function == null"); //$NON-NLS-1$
    if (label == null) throw new NullPointerException("label == null"); //$NON-NLS-1$
    if (lineColor == null) throw new NullPointerException("lineColor == null"); //$NON-NLS-1$
    this.propertyChangeSupport = new PropertyChangeSupport(this);

    this.function = function;
    this.lineColor = lineColor;
    this.label = label;
  }

  /**
   * 関数を取得します。
   * 
   * @return 関数。null以外の値を返します。
   */
  public Function2D getFunction() {
    return this.function;
  }

  /**
   * ラベルを取得します。
   * 
   * @return ラベル。null以外の値を返します。
   */
  public String getLabel() {
    return this.label;
  }

  /**
   * ラベルを設定します。
   * 
   * @param label 文字列。nullは許容しません。
   */
  public void setLabel(String label) {
    if (label == null) throw new NullPointerException();
    final String oldLabel = this.label;
    this.label = label;
    this.propertyChangeSupport.firePropertyChange(LABEL_PROPERTY_NAME, oldLabel, label);
  }

  /**
   * 線の色を取得します。
   * 
   * @return 線の色。null以外の値を返します。
   */
  public Color getLineColor() {
    return this.lineColor;
  }

  /**
   * 線の色を設定します。
   * 
   * @param lineColor 線の色。nullは許容しません。
   */
  public void setLineColor(Color lineColor) {
    if (lineColor == null) throw new NullPointerException();
    final Color oldLineColor = this.lineColor;
    this.lineColor = lineColor;
    this.propertyChangeSupport.firePropertyChange(LINE_COLOR_PROPERTY_NAME, oldLineColor, lineColor);
  }

  /**
   * 線種を取得します。
   * 
   * @return 線種。null以外の値を返します。
   */
  public LineType getLineType() {
    return this.lineType;
  }

  /**
   * 線種を設定します。
   * 
   * @param lineType 線種。nullは許容しません。
   */
  public void setLineType(LineType lineType) {
    if (lineType == null) throw new NullPointerException();
    final LineType oldLineType = this.lineType;
    this.lineType = lineType;
    this.propertyChangeSupport.firePropertyChange(LINE_TYPE_PROPERTY_NAME, oldLineType, lineType);
  }

  /**
   * 線幅を取得します。
   * 
   * @return 線幅(&gt;0)
   */
  public float getLineWidth() {
    return this.lineWidth;
  }

  /**
   * lineWidthを設定します。
   * 
   * @param lineWidth lineWidth
   */
  @SuppressWarnings("boxing")
  public void setLineWidth(float lineWidth) {
    if (lineWidth <= 0) throw new IllegalArgumentException("Line width must be greater than 0."); //$NON-NLS-1$
    final float oldLineWidth = this.lineWidth;
    this.lineWidth = lineWidth;
    this.propertyChangeSupport.firePropertyChange(LINE_WIDTH_PROPERTY_NAME, oldLineWidth, lineWidth);
  }

  /**
   * {@link PropertyChangeListener}を追加します。
   * 
   * @param l {@link PropertyChangeListener}
   */
  public void addPropertyChangeListener(PropertyChangeListener l) {
    this.propertyChangeSupport.addPropertyChangeListener(l);
  }

  /**
   * {@link PropertyChangeListener}を削除します。
   * 
   * @param l {@link PropertyChangeListener}
   */
  public void removePropertyChangeListener(PropertyChangeListener l) {
    this.propertyChangeSupport.removePropertyChangeListener(l);
  }

}
