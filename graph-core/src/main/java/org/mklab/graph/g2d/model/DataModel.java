/**
 * 
 */
package org.mklab.graph.g2d.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// 名前が抽象的すぎ
/**
 * 複数の{@link LineModel}を束ねるモデルです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public class DataModel implements Iterable<LineModel> {

  private List<LineModel> lineModels;
  private List<DataModelListener> listeners;

  /**
   * {@link DataModel}オブジェクトを構築します。
   */
  public DataModel() {
    this.lineModels = new ArrayList<LineModel>();
    this.listeners = new ArrayList<DataModelListener>();
  }

  /**
   * 線の数を取得します。
   * 
   * @return 線の数
   */
  public int getLineCount() {
    return this.lineModels.size();
  }

  /**
   * ラインモデルを取得します。
   * 
   * @param index インデックス
   * @return ラインモデル
   */
  public LineModel getLineModel(int index) {
    return this.lineModels.get(index);
  }

  /**
   * {@link LineModel}を追加します。
   * 
   * @param lineModel 線のモデル
   */
  public void addLineModel(LineModel lineModel) {
    if (this.lineModels.contains(lineModel)) throw new IllegalArgumentException();
    this.lineModels.add(lineModel);
    fireLineModelAdded(lineModel);
  }

  /**
   * ラインモデルを取得します。
   * 
   * @param index インデックス
   */
  public void removeLineModel(int index) {
    LineModel removed = this.lineModels.remove(index);
    fireLineModelRemoved(removed);
  }

  /**
   * {@link LineModel}を削除します。
   * 
   * @param lineModel 線のモデル
   */
  public void removeLineModel(LineModel lineModel) {
    if (this.lineModels.contains(lineModel) == false) throw new IllegalArgumentException();
    this.lineModels.remove(lineModel);
    fireLineModelRemoved(lineModel);
  }

  private void fireLineModelAdded(LineModel lineModel) {
    for (DataModelListener l : this.listeners) {
      l.lineModelAdded(lineModel);
    }
  }

  private void fireLineModelRemoved(LineModel lineModel) {
    for (DataModelListener l : this.listeners) {
      l.lineModelRemoved(lineModel);
    }
  }

  /**
   * {@link DataModelListener}を追加します。
   * 
   * @param l 追加するリスナー
   */
  public void addDataModelListener(DataModelListener l) {
    if (this.listeners.contains(l)) throw new IllegalArgumentException();
    this.listeners.add(l);
  }

  /**
   * {@link DataModelListener}を削除します。
   * 
   * @param l 削除するリスナー
   */
  public void removeDataModelListener(DataModelListener l) {
    if (this.listeners.remove(l) == false) throw new IllegalArgumentException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<LineModel> iterator() {
    return this.lineModels.iterator();
  }

}
