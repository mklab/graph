/*
 * Created on 2010/10/12
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.function;

/**
 * 関数を表すインターフェースです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/12
 * @param <I> 関数に与える引数の型
 * @param <O> 関数の戻り値の型
 */
public interface Function<I, O> {

  /**
   * 関数を実行します。
   * 
   * @param arguments 関数の引数
   * @param returnment 関数の戻り値の入れ物
   */
  void eval(I arguments, ReturnmentContainer<O> returnment);

}
