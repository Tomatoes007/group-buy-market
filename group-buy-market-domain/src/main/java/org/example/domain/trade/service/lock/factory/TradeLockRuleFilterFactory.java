package org.example.domain.trade.service.lock.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.trade.model.entity.GroupBuyActivityEntity;
import org.example.domain.trade.model.entity.TradeLockRuleCommandEntity;
import org.example.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import org.example.domain.trade.service.lock.filter.ActivityUsabilityRuleFilter;
import org.example.domain.trade.service.lock.filter.UserTakeLimitRuleFilter;
import org.example.types.design.framework.link.model2.LinkArmory;
import org.example.types.design.framework.link.model2.chain.BussinessLinkedList;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TradeLockRuleFilterFactory {

    @Bean("tradeRuleFilter")
    public BussinessLinkedList<TradeLockRuleCommandEntity, DynamicContext, TradeLockRuleFilterBackEntity>
    tradeRuleFilter(ActivityUsabilityRuleFilter activityUsabilityRuleFilter, UserTakeLimitRuleFilter userTakeLimitRuleFilter) {
        LinkArmory<TradeLockRuleCommandEntity, TradeLockRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> linkArmory =
                new LinkArmory<>("交易规则过滤链条", activityUsabilityRuleFilter, userTakeLimitRuleFilter);

        return linkArmory.getLogicLink();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext{
        private GroupBuyActivityEntity groupBuyActivity;
    }
}
