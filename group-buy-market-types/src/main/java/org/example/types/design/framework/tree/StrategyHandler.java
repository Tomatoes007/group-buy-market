package org.example.types.design.framework.tree;

/* 策略处理映射器 */

public interface StrategyHandler<T, D, R> {

    StrategyHandler DEFAULT = (T,D) -> null;
    R apply(T requestParameter,D dynamicContext) throws Exception;

}
