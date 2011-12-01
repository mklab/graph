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
package org.mklab.graph.g2d.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.mklab.abgr.Color;
import org.mklab.abgr.LineType;
import org.mklab.graph.function.Function2D;
import org.mklab.graph.g2d.plotter.DefaultPlotterFactory;
import org.mklab.graph.g2d.plotter.Plotter;
import org.mklab.graph.g2d.plotter.PlotterFactory;


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

  /** 関数の描画を行うオブジェクトを生成するファクトリーのプロパティ名です。 */
  public static final String PLOTTER_PROPERTY_NAME = "plotter"; //$NON-NLS-1$
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
  /** 関数を描画するオブジェクトです。 */
  private Plotter plotter;

  private static PlotterFactory plotterFactory = new DefaultPlotterFactory();
  /** {@link PropertyChangeListener}の管理に利用します。 */
  private PropertyChangeSupport propertyChangeSupport;

  /**
   * {@link LineModel}オブジェクトを構築します。
   * 
   * @param label ラベル
   * @param lineColor 線の色
   * @param function 関数
   * @param plotter 関数の描画を行うオブジェクト
   */
  public LineModel(String label, Color lineColor, Function2D function, Plotter plotter) {
    if (function == null) throw new NullPointerException("function == null"); //$NON-NLS-1$
    if (label == null) throw new NullPointerException("label == null"); //$NON-NLS-1$
    if (lineColor == null) throw new NullPointerException("lineColor == null"); //$NON-NLS-1$
    if (plotter == null) throw new NullPointerException("plotter == null"); //$NON-NLS-1$

    this.propertyChangeSupport = new PropertyChangeSupport(this);

    this.function = function;
    this.lineColor = lineColor;
    this.label = label;
    this.plotter = plotter;
  }

  /**
   * {@link LineModel}オブジェクトを構築します。
   * 
   * @param label ラベル
   * @param lineColor 線の色
   * @param function 関数
   */
  public LineModel(String label, Color lineColor, Function2D function) {
    this(label, lineColor, function, plotterFactory.create(function));
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
   * 線幅を設定します。
   * 
   * @param lineWidth 線幅(&gt;0)
   */
  @SuppressWarnings("boxing")
  public void setLineWidth(float lineWidth) {
    if (lineWidth <= 0) throw new IllegalArgumentException("Line width must be greater than 0."); //$NON-NLS-1$
    final float oldLineWidth = this.lineWidth;
    this.lineWidth = lineWidth;
    this.propertyChangeSupport.firePropertyChange(LINE_WIDTH_PROPERTY_NAME, oldLineWidth, lineWidth);
  }

  /**
   * 関数の描画を行うオブジェクトを設定します。
   * 
   * @param plotter 関数の描画を行うオブジェクト
   */
  public void setPlotter(Plotter plotter) {
    if (plotterFactory == null) throw new NullPointerException();
    final Plotter old = this.plotter;
    this.plotter = plotter;
    if (plotter != old) {
      this.propertyChangeSupport.firePropertyChange(PLOTTER_PROPERTY_NAME, old, plotter);
    }
  }

  /**
   * 関数の描画を行うオブジェクトを取得します。
   * 
   * @return 関数の描画を行うオブジェクト
   */
  public Plotter getPlotter() {
    return this.plotter;
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
