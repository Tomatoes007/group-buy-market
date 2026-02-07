package org.example.domain.trade.adapter.port;

import org.example.domain.trade.model.entity.NotifyTaskEntity;


public interface ITradePort {
    String groupBuyNotify(NotifyTaskEntity notifyTaskEntity) throws Exception;
}