package org.example.domain.activity.service.trial.node;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.entity.MarketProductEntity;
import org.example.domain.activity.model.entity.TrialBalanceEntity;
import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import org.example.domain.activity.model.valobj.SkuVO;
import org.example.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import org.example.domain.activity.service.trial.Thread.QueryGroupBuyActivityDiscountVOThreadTask;
import org.example.domain.activity.service.trial.Thread.QuerySkuVOFromDBThreadTask;
import org.example.domain.activity.service.trial.factory.DefaultActivityStratetyFactory;
import org.example.types.design.framework.tree.StrategyHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStratetyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private EndNode endNode;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStratetyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        QueryGroupBuyActivityDiscountVOThreadTask queryGroupBuyActivityDiscountVOThreadTask = new QueryGroupBuyActivityDiscountVOThreadTask(requestParameter.getSource(), requestParameter.getChannel(), repository);
        FutureTask<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOFutureTask = new FutureTask<>(queryGroupBuyActivityDiscountVOThreadTask);
        threadPoolExecutor.execute(groupBuyActivityDiscountVOFutureTask);

        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask = new QuerySkuVOFromDBThreadTask(requestParameter.getGoodsId(), repository);
        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        threadPoolExecutor.execute(skuVOFutureTask);
        dynamicContext.setGroupBuyActivityDiscountVO(groupBuyActivityDiscountVOFutureTask.get(timeout, TimeUnit.MINUTES));
        dynamicContext.setSkuVO(skuVOFutureTask.get(timeout,TimeUnit.MINUTES));
    }

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStratetyFactory.DynamicContext dynamicContext) throws Exception {
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStratetyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStratetyFactory.DynamicContext dynamicContext) {
        return endNode;
    }
}
