package org.example.domain.trade.service;

import org.example.domain.trade.model.entity.TradePaySettlementEntity;
import org.example.domain.trade.model.entity.TradePaySuccessEntity;

import java.util.Map;

public interface ITradeSettlementOrderService {
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccess) throws Exception;

    Map<String,Integer> execSettlementNotifyJob() throws Exception;

    Map<String,Integer> execSettlementNotifyJob(String teamId) throws Exception;

}
