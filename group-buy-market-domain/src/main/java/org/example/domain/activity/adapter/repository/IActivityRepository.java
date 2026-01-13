package org.example.domain.activity.adapter.repository;

import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import org.example.domain.activity.model.valobj.SCSkuActivityVO;
import org.example.domain.activity.model.valobj.SkuVO;

public interface IActivityRepository {
    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);
    SkuVO querySkuByGoodsId(String goodsId);
    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);
}
