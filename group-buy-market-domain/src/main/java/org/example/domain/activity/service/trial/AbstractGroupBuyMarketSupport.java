package org.example.domain.activity.service.trial;

import org.example.domain.activity.adapter.repository.IActivityRepository;
import org.example.types.design.framework.tree.AbstractMultiThreadStrategyRouter;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity,DynamicContext,TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity,DynamicContext,TrialBalanceEntity> {

    protected long timeout=500;

    @Resource
    protected IActivityRepository repository;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {

    }
}
