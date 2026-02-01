package org.example.domain.trade.adapter.repository;

import org.example.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import org.example.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import org.example.domain.trade.model.entity.GroupBuyActivityEntity;
import org.example.domain.trade.model.entity.GroupBuyTeamEntity;
import org.example.domain.trade.model.entity.MarketPayOrderEntity;
import org.example.domain.trade.model.valobj.GroupBuyProgressVO;

public interface ITradeRepository {

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    Integer queryOrderCountByActivityId(Long activityId, String userId);

    GroupBuyActivityEntity queryGroupBuyActivityByActivityId(Long activityId);

    GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId);

    void settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate);

    boolean isSCBlackIntercept(String source, String channel);
}
