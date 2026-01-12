package org.example.domain.activity.service.discount.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import org.example.domain.activity.service.discount.AbstractDiscountCalculateService;
import org.example.types.common.Constants;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Slf4j
@Service("MJ")
public class MJCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        String marketExpr=groupBuyDiscount.getMarketExpr();
        String[] split = marketExpr.split(Constants.SPLIT);
        BigDecimal x=new BigDecimal(split[0]);
        BigDecimal y=new BigDecimal(split[1]);
        if(originalPrice.compareTo(x)<0){
            return originalPrice;
        }
        BigDecimal deductPrice=originalPrice.subtract(y);
        if(deductPrice.compareTo(BigDecimal.ZERO)<0){
            return new BigDecimal(0.01);
        }
        return deductPrice;
    }
}
