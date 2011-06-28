/**
 * 
 */
package org.mklab.ishikura.graph.function;

/**
 * 関数の戻り値を表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 * @param <T> 関数の戻り値の型
 */
public class ReturnmentContainer<T> {

  private T value;

  /**
   * 関数の戻り値を設定します。
   * 
   * @param value 関数の戻り値
   */
  public void set(T value) {
    this.value = value;
  }

  /**
   * 関数の戻り値を取得します。
   * 
   * @return 関数の戻り値
   */
  public T get() {
    return this.value;
  }

}
