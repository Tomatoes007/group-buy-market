package org.example.domain.trade.service.lock;

import org.example.domain.trade.adapter.repository.ITradeRepository;
import org.example.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import org.example.domain.trade.model.entity.*;
import org.example.domain.trade.model.valobj.GroupBuyProgressVO;
import org.example.domain.trade.service.ITradeLockOrderService;
import org.example.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import org.example.types.design.framework.link.model2.chain.BussinessLinkedList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TradeLockOrderService implements ITradeLockOrderService {

    @Resource
    private ITradeRepository repository;

    @Resource
    private BussinessLinkedList<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> tradeRuleFilter;

    @Override
    public GroupBuyProgressVO queryGroupBuyProgress(String teamId) {
        return repository.queryGroupBuyProgress(teamId);
    }

    @Override
    public MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo) {
        return repository.queryNoPayMarketPayOrderByOutTradeNo(userId,outTradeNo);
    }

    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception {

        TradeLockRuleFilterBackEntity tradeRuleFilterBackEntity = tradeRuleFilter.apply(TradeLockRuleCommandEntity.builder()
                        .activityId(payActivityEntity.getActivityId())
                        .userId(userEntity.getUserId()).build()
                , new TradeLockRuleFilterFactory.DynamicContext());

        Integer userTakeOrderCount = tradeRuleFilterBackEntity.getUserTakeOrderCount();

        GroupBuyOrderAggregate groupBuyOrderAggregate = GroupBuyOrderAggregate.builder()
                .userEntity(userEntity)
                .payActivityEntity(payActivityEntity)
                .payDiscountEntity(payDiscountEntity)
                .userTakeOrderCount(userTakeOrderCount)
                .build();
        return repository.lockMarketPayOrder(groupBuyOrderAggregate);
    }
}
