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
package org.mklab.graph.jcas;

import org.mklab.graph.function.ContinuousFunction2D;
import org.mklab.ishikura.jcas.exp.Constant;
import org.mklab.ishikura.jcas.exp.Expression;
import org.mklab.ishikura.jcas.exp.ExpressionParser;
import org.mklab.ishikura.jcas.exp.FunctionExpression;
import org.mklab.ishikura.jcas.func.Function;
import org.mklab.ishikura.jcas.func.FunctionEvalationException;
import org.mklab.ishikura.jcas.parser.ParseException;


/**
 * jcasによる{@link ContinuousFunction2D}です。
 * <p>
 * 文字列による関数をパースし、{@link ContinuousFunction2D}を生成します。
 * 
 * @author ishikura
 * @version $Revision$, 2011/07/03
 */
public class JcasContinuousFunction2D extends ContinuousFunction2D {

  Function function;

  /**
   * 与えられた式をパースし、関数を生成します。
   * 
   * @param equation 式
   * @return 関数
   */
  public static ContinuousFunction2D parse(String equation) {
    final ExpressionParser parser = new ExpressionParser();
    try {
      final Expression expression = parser.parse(equation);
      if (expression instanceof FunctionExpression == false) {
        throw new IllegalArgumentException("Not a function."); //$NON-NLS-1$
      }
      final Function f = ((FunctionExpression)expression).getFunction();
      return new JcasContinuousFunction2D(f);
    } catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * {@link JcasContinuousFunction2D}オブジェクトを構築します。
   * 
   * @param function 関数
   */
  private JcasContinuousFunction2D(Function function) {
    if (function == null) throw new NullPointerException();
    this.function = function;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double evalY(double x) {
    try {
      final Expression result = this.function.evaluate(new Expression[] {new Constant(x)});
      final Expression simplifiedResult = result.simplify();
      if (simplifiedResult instanceof Constant == false) throw new IllegalStateException("Not resolved. Invalid function?"); //$NON-NLS-1$

      final Constant resultConstant = (Constant)simplifiedResult;
      return resultConstant.getValue();
    } catch (FunctionEvalationException e) {
      throw new RuntimeException(e);
    }
  }

}
