package org.example.domain.activity.service.discount;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.adapter.repository.IActivityRepository;
import org.example.domain.activity.model.valobj.DiscountTypeEnum;
import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {

    @Resource
    protected IActivityRepository   repository;

    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        if(DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())){
            boolean isCrowdRange =filterTagId(userId,groupBuyDiscount.getTagId());
            if(!isCrowdRange){
                log.info("用户不符合人群标签条件 userId:{}",userId);// 添加日志内容
                return originalPrice;
            }
        }
        return doCalculate(originalPrice,groupBuyDiscount);
    }

    private boolean filterTagId(String userId,String tagId) {
        return repository.isTagCrowRange(tagId,userId);
    }
    protected abstract BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);
}
