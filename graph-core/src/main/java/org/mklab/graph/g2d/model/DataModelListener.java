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
