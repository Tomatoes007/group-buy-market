package org.example.infrastructure.adapter.port;

import org.example.domain.trade.adapter.port.ITradePort;
import org.example.domain.trade.model.entity.NotifyTaskEntity;
import org.example.infrastructure.gateway.GroupBuyNotifyService;
import org.example.infrastructure.redis.IRedisService;
import org.example.types.enums.NotifyTaskHTTPEnumVO;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: xt-code
 * @date: 2025/5/14 10:21
 * @description:
 */
@Service
public class TradePort implements ITradePort {
    @Resource
    private GroupBuyNotifyService groupBuyNotifyService;

    /**
     * 使用一个分布式锁，作为一种资源，谁先抢到它。谁先用
     */
    @Resource
    private IRedisService redisService;


    @Override
    public String groupBuyNotify(NotifyTaskEntity notifyTaskEntity) throws Exception {
        RLock lock = redisService.getLock(notifyTaskEntity.lockKey());

        try {
            // group-buy-market 拼团服务端会被部署到多台应用服务器上，那么就会有很多任务一起执行。这个时候要进行抢占，避免被多次执行
            if (lock.tryLock(3, 0, TimeUnit.SECONDS)) {
                try {

                    // 无效的 notifyUrl 则直接返回成功
                    if (StringUtils.isBlank(notifyTaskEntity.getNotifyUrl()) || "暂无".equals(notifyTaskEntity.getNotifyUrl())) {
                        return NotifyTaskHTTPEnumVO.SUCCESS.getCode();
                    }

                    //-----------真实的远程调用
                    return groupBuyNotifyService.groupBuyNotify(notifyTaskEntity.getNotifyUrl(), notifyTaskEntity.getParameterJson());

                } finally {
                    //加锁状态，并且由当前线程持有
                    if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
            //返回Null
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        } catch (Exception exception) {
            Thread.currentThread().interrupt();
            return NotifyTaskHTTPEnumVO.NULL.getCode();
        }
    }
}