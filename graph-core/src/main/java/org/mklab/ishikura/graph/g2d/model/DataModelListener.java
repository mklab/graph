/**
 * 
 */
package org.mklab.ishikura.graph.g2d.model;

/**
 * {@link DataModel}を監視するリスナーです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2011/07/02
 */
public interface DataModelListener {

  /**
   * 線が追加されたときに呼び出されます。
   * 
   * @param lineModel 追加されたモデル
   */
  void lineModelAdded(LineModel lineModel);

  /**
   * 線が削除されたときに呼び出されます。
   * 
   * @param lineModel 削除されたモデル
   */
  void lineModelRemoved(LineModel lineModel);

}
