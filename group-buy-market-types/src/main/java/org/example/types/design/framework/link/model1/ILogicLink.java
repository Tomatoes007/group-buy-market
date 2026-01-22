package org.example.types.design.framework.link.model1;

public interface ILogicLink<T, D, R> extends ILogicChainArmory<T, D, R> {

    /**
     * 链路负责执行任务的方法
     * @param requestParameter  入参
     * @param dynamicContext    动态上下文存储数据
     * @return                  结果
     * @throws Exception        异常
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;
}