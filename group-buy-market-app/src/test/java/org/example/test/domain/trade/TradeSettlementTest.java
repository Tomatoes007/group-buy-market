package org.example.test.domain.trade;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.trade.model.entity.TradePaySettlementEntity;
import org.example.domain.trade.model.entity.TradePaySuccessEntity;
import org.example.domain.trade.service.ITradeSettlementOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: xt-code
 * @date: 2025/5/12 17:13
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TradeSettlementTest {
    @Resource
    private ITradeSettlementOrderService tradeSettlementOrderService;

    //结算服务
    @Test
    public void test_settlementMarketPayOrder() throws Exception {
        TradePaySuccessEntity tradePaySuccessEntity = new TradePaySuccessEntity();
        tradePaySuccessEntity.setSource("s01");
        tradePaySuccessEntity.setChannel("c01");
        tradePaySuccessEntity.setUserId("lzm01");
        tradePaySuccessEntity.setOutTradeNo("382273897857");
        tradePaySuccessEntity.setOutTradeTime(new Date());
        TradePaySettlementEntity tradePaySettlementEntity = tradeSettlementOrderService.settlementMarketPayOrder(tradePaySuccessEntity);
        log.info("请求参数:{}", JSON.toJSONString(tradePaySuccessEntity));
        log.info("测试结果:{}", JSON.toJSONString(tradePaySettlementEntity));
    }
}