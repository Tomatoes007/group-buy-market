package org.example.domain.activity.adapter.repository;

import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import org.example.domain.activity.model.valobj.SkuVO;

public interface IActivityRepository {
    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source,String channel);
    SkuVO querySkuByGoodsId(String goodsId);
}
