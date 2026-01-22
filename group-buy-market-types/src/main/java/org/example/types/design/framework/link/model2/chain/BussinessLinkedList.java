package org.example.types.design.framework.link.model2.chain;

import org.example.types.design.framework.link.model2.handler.ILogicHandler;

public class BussinessLinkedList<T, D, R> extends LinkedList<ILogicHandler<T, D, R>> implements ILogicHandler<T, D, R>{

    /**
     * 构造函数：初始化业务链路名称
     *
     * @param name 链路名称（用于标识链路用途，如"审批链"）
     */
    public BussinessLinkedList(String name) {
        super(name);
    }


    /**
     * 核心逻辑方法：按顺序执行责任链中的处理器
     *
     * @param requestParameter 请求参数（泛型 T，如订单信息）
     * @param dynamicContext 动态上下文（泛型 D，如用户身份、环境变量）
     * @return 处理结果（泛型 R，如审批通过/拒绝）
     * @throws Exception 处理器执行异常
     */
    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {

        /**
         * 从链表头节点开始遍历
         * this.first是默认属性，可以在本类、本包中使用，它们在一个包内。
         */
        Node<ILogicHandler<T, D, R>> current = this.first;

        //do-while至少执行一次业务
        do {
            //获取当前节点的处理器
            ILogicHandler<T, D, R> item = current.item;

            //执行处理器的apply方法
            R apply = item.apply(requestParameter, dynamicContext);

            // 如果处理器返回非空结果，则终止链路并返回结果
            if (null != apply) return apply;

            // 移动到下一个节点
            current = current.next;
        } while (null != current);

        // 遍历完整个链路后无有效结果，返回 null（或默认值）
        return null;
    }
}