package org.example.types.design.framework.link.model2.handler;

public interface ILogicHandler<T,D,R> {
    default R next(T Requestparameter, D dynamicContext) {
        return null;
    }
    R apply(T requestParameter, D dynamicContext) throws Exception;
}
