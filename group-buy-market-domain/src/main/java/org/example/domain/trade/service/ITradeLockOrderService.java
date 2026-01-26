package org.example.domain.trade.service;

import org.example.domain.trade.model.entity.MarketPayOrderEntity;
import org.example.domain.trade.model.entity.PayActivityEntity;
import org.example.domain.trade.model.entity.PayDiscountEntity;
import org.example.domain.trade.model.entity.UserEntity;
import org.example.domain.trade.model.valobj.GroupBuyProgressVO;

public interface ITradeLockOrderService {

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId,String outTradeNo);

    MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception;
}
