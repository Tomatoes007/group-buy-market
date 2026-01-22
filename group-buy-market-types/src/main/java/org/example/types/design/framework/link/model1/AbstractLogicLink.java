package org.example.types.design.framework.link.model1;

public abstract class AbstractLogicLink<T, D, R> implements ILogicLink<T, D, R> {
    //当前节点的下一个节点
    private ILogicLink<T, D, R> next;

    @Override
    public ILogicLink<T, D, R> next() {
        return next;
    }

    @Override
    public ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next) {
        this.next = next;
        return next;
    }


    /**
     * 执行当前节点  下一个节点的处理方法
     * @param requestParameter  入参
     * @param dynamicContext    动态上下文
     * @return                  返回值
     * @throws Exception        异常
     */
    protected R next(T requestParameter, D dynamicContext) throws Exception {
        return next.apply(requestParameter, dynamicContext);
    }
}