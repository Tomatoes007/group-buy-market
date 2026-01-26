package org.example.domain.trade.service.settlement;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.trade.adapter.repository.ITradeRepository;
import org.example.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import org.example.domain.trade.model.entity.*;
import org.example.domain.trade.service.ITradeSettlementOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TradeSettlementOrderService implements ITradeSettlementOrderService {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccess) {

        MarketPayOrderEntity marketPayOrderEntity = repository.queryNoPayMarketPayOrderByOutTradeNo(tradePaySuccess.getUserId(), tradePaySuccess.getOutTradeNo());
        if(null==marketPayOrderEntity){
            return null;
        }

        GroupBuyTeamEntity groupBuyTeamEntity = repository.queryGroupBuyTeamByTeamID(marketPayOrderEntity.getTeamId());

        GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate = GroupBuyTeamSettlementAggregate.builder()
                .userEntity(UserEntity.builder()
                        .userId(tradePaySuccess.getUserId()).build())
                .groupBuyTeamEntity(groupBuyTeamEntity)
                .tradePaySuccessEntity(tradePaySuccess)
                .build();

        repository.settlementMarketPayOrder(groupBuyTeamSettlementAggregate);

        return TradePaySettlementEntity.builder()
                .source(tradePaySuccess.getSource())
                .channel(tradePaySuccess.getChannel())
                .userId(tradePaySuccess.getUserId())
                .teamId(marketPayOrderEntity.getTeamId())
                .activityId(groupBuyTeamEntity.getActivityId())
                .outTradeNo(tradePaySuccess.getOutTradeNo())
                .build();
    }
}
