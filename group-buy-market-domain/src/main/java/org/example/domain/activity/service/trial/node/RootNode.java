package org.example.domain.activity.service.trial.node;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.domain.activity.model.entity.MarketProductEntity;
import org.example.domain.activity.model.entity.TrialBalanceEntity;
import org.example.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import org.example.domain.activity.service.trial.factory.DefaultActivityStratetyFactory;
import org.example.types.design.framework.tree.StrategyHandler;
import org.example.types.enums.ResponseCode;
import org.example.types.exception.AppException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class RootNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStratetyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private SwitchNode switchNode;

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStratetyFactory.DynamicContext dynamicContext) throws Exception {
        if(StringUtils.isBlank(requestParameter.getUserId())||StringUtils.isBlank(requestParameter.getGoodsId())
            ||StringUtils.isBlank(requestParameter.getChannel())||StringUtils.isBlank(requestParameter.getSource())){
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(),ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStratetyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStratetyFactory.DynamicContext dynamicContext) {
        return switchNode;
    }
}
