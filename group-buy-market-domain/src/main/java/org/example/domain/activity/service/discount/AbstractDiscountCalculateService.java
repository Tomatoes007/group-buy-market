package org.example.domain.activity.service.discount;

import org.example.domain.activity.model.valobj.DiscountTypeEnum;
import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {
    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        if(DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
            boolean isCrowdRange =filterTagId(groupBuyDiscount.getTagId());
        }
        return doCalculate(originalPrice,groupBuyDiscount);
    }

    private boolean filterTagId(String tagId) {
        return true;
    }
    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}
