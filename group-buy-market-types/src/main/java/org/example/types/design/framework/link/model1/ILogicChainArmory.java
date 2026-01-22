package org.example.types.design.framework.link.model1;

public interface ILogicChainArmory<T, D, R> {
    /**
     * 返回当前节点的下一个节点
     * @return 返回下一个节点
     */
    ILogicLink<T, D, R> next();

    /**
     * 设置责任链的下一个节点
     * @param next  当前节点的下一个节点
     * @return      下一个节点
     */
    ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next);
}