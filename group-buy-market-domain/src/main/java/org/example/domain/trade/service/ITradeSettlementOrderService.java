package org.example.domain.trade.service;

import org.example.domain.trade.model.entity.TradePaySettlementEntity;
import org.example.domain.trade.model.entity.TradePaySuccessEntity;

public interface ITradeSettlementOrderService {
    TradePaySettlementEntity settlementMarketPayOrder(TradePaySuccessEntity tradePaySuccess) throws Exception;
}
