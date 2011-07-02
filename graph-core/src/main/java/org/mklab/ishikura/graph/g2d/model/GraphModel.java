/**
 * 
 */
package org.mklab.ishikura.graph.g2d.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.mklab.ishikura.graph.g2d.ColorConstants;
import org.mklab.ishikura.graph.graphics.Color;


/**
 * 二次元グラフのモデルを表すクラスです。
 * <p>
 * {@link LineModel}をのぞくすべてのプロパティの変更を監視出来ます。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public class GraphModel {

  /** 関数情報表示ボックスプロパティの名前です。 */
  public static final String INFO_BOX_BACKGROUND_COLOR_PROPERTY_NAME = "infoBoxBackgroundColor"; //$NON-NLS-1$
  /** グリッド背景色プロパティの名前です。 */
  public static final String GRID_BACKGROUND_COLOR_PROPERTY_NAME = "gridBackgroundColor"; //$NON-NLS-1$
  /** グリッドの枠の色のプロパティの名前です。 */
  public static final String GRID_BORDER_COLOR_PROPERTY_NAME = "gridBorderColor"; //$NON-NLS-1$
  /** 前景色プロパティの名前です。 */
  public static final String FOREGROUND_COLOR_PROPERTY_NAME = "foregroundColor"; //$NON-NLS-1$
  /** 背景色プロパティの名前です。 */
  public static final String BACKGROUND_COLOR_PROPERTY_NAME = "backgroundColor"; //$NON-NLS-1$
  /** y軸名プロパティの名前です。 */
  public static final String Y_AXIS_NAME_PROPERTY_NAME = "yAxisName"; //$NON-NLS-1$
  /** x軸名プロパティの名前です。 */
  public static final String X_AXIS_NAME_PROPERTY_NAME = "xAxisName"; //$NON-NLS-1$
  /** タイトルプロパティの名前です。 */
  public static final String TITLE_PROPERTY_NAME = "title"; //$NON-NLS-1$

  /** グラフのタイトルです。 */
  private String title = null;
  /** x軸の名前です。 */
  private String xAxisName = null;
  /** y軸の名前です。 */
  private String yAxisName = null;
  /** 線のモデルのリストです。 */
  private DataModel dataModel;

  /** グラフ全体の背景色です。 */
  private Color backgroundColor = ColorConstants.BACKGROUND;
  /** グラフのタイトル、x軸、y軸のラベルの色です。 */
  private Color foregroundColor = ColorConstants.FOREGROUND;
  /** 格子の背景色です。 */
  private Color gridBackgroundColor = ColorConstants.COORDINATES_BACKGROUND;
  /** 格子の枠の色です。 */
  private Color gridBorderColor = ColorConstants.COORDINATES_BORDER;
  /** 情報表示ボックスの背景色です。 */
  private Color infoBoxBackgroundColor = ColorConstants.FUNCTION_INFO_BACKGROUND;

  /** {@link PropertyChangeListener}の管理に利用します。 */
  private PropertyChangeSupport propertyChangeSupport;

  /**
   * {@link GraphModel}オブジェクトを構築します。
   */
  public GraphModel() {
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.dataModel = new DataModel();
  }

  /**
   * {@link LineModel}を追加します。
   * 
   * @param lineModel 線のモデル
   */
  public void addLineModel(LineModel lineModel) {
    this.dataModel.addLineModel(lineModel);
  }

  /**
   * データモデルを取得します。
   * 
   * @return データモデル
   */
  public DataModel getDataModel() {
    return this.dataModel;
  }

  /**
   * グラフのタイトルを取得します。
   * 
   * @return グラフのタイトル
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * グラフのタイトルを設定します。
   * 
   * @param title グラフのタイトル
   */
  public void setTitle(String title) {
    final String oldTitle = this.title;
    this.title = title;
    this.propertyChangeSupport.firePropertyChange(TITLE_PROPERTY_NAME, oldTitle, title);
  }

  /**
   * x軸の名前を取得します。
   * 
   * @return x軸の名前
   */
  public String getXAxisName() {
    return this.xAxisName;
  }

  /**
   * x軸の名前を設定します。
   * 
   * @param xAxisName x軸の名前
   */
  public void setXAxisName(String xAxisName) {
    final String oldXAxisName = this.xAxisName;
    this.xAxisName = xAxisName;
    this.propertyChangeSupport.firePropertyChange(X_AXIS_NAME_PROPERTY_NAME, oldXAxisName, xAxisName);
  }

  /**
   * y軸の名前を取得します。
   * 
   * @return y軸の名前
   */
  public String getyAxisName() {
    return this.yAxisName;
  }

  /**
   * y軸の名前を設定します。
   * 
   * @param yAxisName y軸の名前
   */
  public void setYAxisName(String yAxisName) {
    final String oldYAxisName = this.yAxisName;
    this.yAxisName = yAxisName;
    this.propertyChangeSupport.firePropertyChange(Y_AXIS_NAME_PROPERTY_NAME, oldYAxisName, yAxisName);
  }

  /**
   * グラフ全体の背景色を取得します。
   * 
   * @return グラフ全体の背景色
   */
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }

  /**
   * グラフ全体の背景色を設定します。
   * 
   * @param backgroundColor グラフ全体の背景色
   */
  public void setBackgroundColor(Color backgroundColor) {
    final Color oldBackgroundColor = this.backgroundColor;
    this.backgroundColor = backgroundColor;
    this.propertyChangeSupport.firePropertyChange(BACKGROUND_COLOR_PROPERTY_NAME, oldBackgroundColor, backgroundColor);
  }

  /**
   * ラベルの描画に利用する前景色を取得します。
   * 
   * @return 前景色
   */
  public Color getForegroundColor() {
    return this.foregroundColor;
  }

  /**
   * ラベルの描画に利用する前景色を設定します。
   * 
   * @param foregroundColor 前景色
   */
  public void setForegroundColor(Color foregroundColor) {
    final Color oldForegroundColor = this.foregroundColor;
    this.foregroundColor = foregroundColor;
    this.propertyChangeSupport.firePropertyChange(FOREGROUND_COLOR_PROPERTY_NAME, oldForegroundColor, foregroundColor);
  }

  /**
   * グラフ格子部分の背景色を取得します。
   * 
   * @return グラフ格子部分の背景色
   */
  public Color getGridBackgroundColor() {
    return this.gridBackgroundColor;
  }

  /**
   * グラフ格子部分の背景色を設定します。
   * 
   * @param gridBackgroundColor グラフ格子部分の背景色
   */
  public void setGridBackgroundColor(Color gridBackgroundColor) {
    final Color oldGridBackgroundColor = this.gridBackgroundColor;
    this.gridBackgroundColor = gridBackgroundColor;
    this.propertyChangeSupport.firePropertyChange(GRID_BACKGROUND_COLOR_PROPERTY_NAME, oldGridBackgroundColor, gridBackgroundColor);
  }

  /**
   * グラフ格子部分の枠の色を取得します。
   * 
   * @return グラフ格子部分の枠の色
   */
  public Color getGridBorderColor() {
    return this.gridBorderColor;
  }

  /**
   * グラフ格子部分の枠の色を設定します。
   * 
   * @param gridBorderColor グラフ格子部分の枠の色
   */
  public void setGridBorderColor(Color gridBorderColor) {
    final Color oldGridBorderColor = this.gridBorderColor;
    this.gridBorderColor = gridBorderColor;
    this.propertyChangeSupport.firePropertyChange(GRID_BORDER_COLOR_PROPERTY_NAME, oldGridBorderColor, gridBorderColor);
  }

  /**
   * 関数情報表示ボックスの背景色を取得します。
   * 
   * @return 関数情報表示ボックスの背景色
   */
  public Color getInfoBoxBackgroundColor() {
    return this.infoBoxBackgroundColor;
  }

  /**
   * 関数情報表示ボックスの背景色を設定します。
   * 
   * @param infoBoxBackgroundColor 関数情報表示ボックスの背景色
   */
  public void setInfoBoxBackgroundColor(Color infoBoxBackgroundColor) {
    final Color oldInfoBoxBackgroundColor = this.infoBoxBackgroundColor;
    this.infoBoxBackgroundColor = infoBoxBackgroundColor;
    this.propertyChangeSupport.firePropertyChange(INFO_BOX_BACKGROUND_COLOR_PROPERTY_NAME, oldInfoBoxBackgroundColor, infoBoxBackgroundColor);
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
