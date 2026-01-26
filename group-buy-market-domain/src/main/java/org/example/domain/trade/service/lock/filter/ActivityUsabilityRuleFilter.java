package org.example.domain.trade.service.lock.filter;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.trade.adapter.repository.ITradeRepository;
import org.example.domain.trade.model.entity.GroupBuyActivityEntity;
import org.example.domain.trade.model.entity.TradeLockRuleCommandEntity;
import org.example.domain.trade.model.entity.TradeLockRuleFilterBackEntity;
import org.example.domain.trade.service.lock.factory.TradeRuleFilterFactory;
import org.example.types.design.framework.link.model2.handler.ILogicHandler;
import org.example.types.enums.ActivityStatusEnumVO;
import org.example.types.enums.ResponseCode;
import org.example.types.exception.AppException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class ActivityUsabilityRuleFilter implements ILogicHandler<TradeLockRuleCommandEntity, TradeRuleFilterFactory.DynamicContext, TradeLockRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeLockRuleFilterBackEntity apply(TradeLockRuleCommandEntity requestParameter, TradeRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易规则过滤-用户活动可用性检验 userId{} activityId:{}", requestParameter.getUserId(), requestParameter.getActivityId());
        GroupBuyActivityEntity groupBuyActivity = repository.queryGroupBuyActivityByActivityId(requestParameter.getActivityId());
        //1.当前活动并不生效
        if(!ActivityStatusEnumVO.EFFECTIVE.equals(groupBuyActivity.getStatus())){
            throw new AppException(ResponseCode.E0101);
        }

        //2.判断当前时间是否处于生效期内
        Date currentTime = new Date();
        if(currentTime.before(groupBuyActivity.getStartTime()) || currentTime.after(groupBuyActivity.getEndTime())){
            throw new AppException(ResponseCode.E0102);
        }

        dynamicContext.setGroupBuyActivity(groupBuyActivity);

        return next(requestParameter, dynamicContext);
    }
}
