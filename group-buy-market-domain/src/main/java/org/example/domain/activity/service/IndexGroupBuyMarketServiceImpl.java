package org.example.domain.activity.service;

import org.example.domain.activity.model.entity.MarketProductEntity;
import org.example.domain.activity.model.entity.TrialBalanceEntity;
import org.example.domain.activity.service.trial.factory.DefaultActivityStratetyFactory;
import org.example.types.design.framework.tree.StrategyHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexGroupBuyMarketServiceImpl implements IndexGroupBuyMarketService {

    @Resource
    private DefaultActivityStratetyFactory activityStratetyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProduct) throws Exception {
        StrategyHandler<MarketProductEntity, DefaultActivityStratetyFactory.DynamicContext, TrialBalanceEntity> strategyHandler=activityStratetyFactory.strategyHandler();
        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProduct, new DefaultActivityStratetyFactory.DynamicContext());
        return trialBalanceEntity;
    }
}
