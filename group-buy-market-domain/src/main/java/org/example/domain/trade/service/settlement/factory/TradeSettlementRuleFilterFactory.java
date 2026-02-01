package org.example.domain.trade.service.settlement.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.trade.model.entity.*;
import org.example.domain.trade.service.lock.factory.TradeLockRuleFilterFactory;
import org.example.domain.trade.service.settlement.filter.EndRuleFilter;
import org.example.domain.trade.service.settlement.filter.OutTradeNoRuleFilter;
import org.example.domain.trade.service.settlement.filter.SCRuleFilter;
import org.example.domain.trade.service.settlement.filter.SettableRuleFilter;
import org.example.types.design.framework.link.model2.LinkArmory;
import org.example.types.design.framework.link.model2.chain.BussinessLinkedList;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TradeSettlementRuleFilterFactory {

    @Bean("tradeSettlementRuleFilter")
    public BussinessLinkedList<TradeSettlementRuleCommandEntity,
            TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> tradeSettlementRuleFilter(
            SCRuleFilter scRuleFilter, OutTradeNoRuleFilter outTradeNoRuleFilter, SettableRuleFilter settableRuleFilter, EndRuleFilter endRuleFilter){
        LinkArmory<TradeSettlementRuleCommandEntity,
                TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> linkArmory =
                new LinkArmory<>("交易结算过滤链条", scRuleFilter, outTradeNoRuleFilter,settableRuleFilter,endRuleFilter);

        return linkArmory.getLogicLink();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext{
        private MarketPayOrderEntity marketPayOrderEntity;
        private GroupBuyTeamEntity groupBuyTeamEntity;
    }
}

