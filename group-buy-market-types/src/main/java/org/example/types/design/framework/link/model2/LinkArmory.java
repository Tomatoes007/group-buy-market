package org.example.types.design.framework.link.model2;

import org.example.types.design.framework.link.model2.chain.BussinessLinkedList;
import org.example.types.design.framework.link.model2.handler.ILogicHandler;

public class LinkArmory<T, D, R> {

    /**
     * 这个类，就是为了装配这个责任链
     */
    private final BussinessLinkedList<T, D, R> logicLink;

    /**
     * @param linkName      待装配的责任链
     * @param logicHandlers 责任链的节点
     */
    @SafeVarargs
    public LinkArmory(String linkName, ILogicHandler<T, D, R>... logicHandlers) {
        logicLink = new BussinessLinkedList<>(linkName);
        for (ILogicHandler<T, D, R> logicHandler : logicHandlers) {
            logicLink.add(logicHandler);
        }
    }

    public BussinessLinkedList<T, D, R> getLogicLink() {
        return logicLink;
    }

}