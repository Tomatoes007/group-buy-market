package org.example.domain.activity.service.discount.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import org.example.domain.activity.service.discount.AbstractDiscountCalculateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service("ZK")
public class ZKCalculateService extends AbstractDiscountCalculateService {
    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        String marketExpr = groupBuyDiscount.getMarketExpr();
        BigDecimal newPrice = originalPrice.multiply(new BigDecimal(marketExpr));
        if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
            return new BigDecimal(0.01);
        }
        return newPrice;
    }
}
