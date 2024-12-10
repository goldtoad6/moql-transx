/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.datayoo.moql.querier.milvus24;

import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;
import org.datayoo.moql.operand.function.AbstractFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Tang Tadin
 */
public class WeightedRanker extends AbstractFunction {

  public static final String FUNCTION_NAME = "weightedRanker";

  protected List<Float> weights = new LinkedList<>();

  public WeightedRanker(List<Operand> parameters) {
    super(FUNCTION_NAME, -1, parameters);
    for (Operand parameter : parameters) {
      Double d = (Double) parameter.operate((EntityMap) null);
      weights.add(d.floatValue());
    }
  }

  /* (non-Javadoc)
   * @see org.moql.operand.function.AbstractFunction#innerOperate(org.moql.data.EntityMap)
   */
  @Override
  protected Object innerOperate(EntityMap entityMap) {
    throw new UnsupportedOperationException();
  }

  @Override
  protected Object innerOperate(Object[] entityArray) {
    throw new UnsupportedOperationException();
  }

  public io.milvus.v2.service.vector.request.ranker.WeightedRanker getWeightedRanker() {
    return new io.milvus.v2.service.vector.request.ranker.WeightedRanker(
        weights);
  }
}