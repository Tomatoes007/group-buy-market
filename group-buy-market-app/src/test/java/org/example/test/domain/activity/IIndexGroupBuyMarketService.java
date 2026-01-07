package org.example.test.domain.activity;


import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.entity.MarketProductEntity;
import org.example.domain.activity.model.entity.TrialBalanceEntity;
import org.example.domain.activity.service.IndexGroupBuyMarketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class IIndexGroupBuyMarketService {

    @Resource
    private IndexGroupBuyMarketService indexGroupBuyMarketService;

    @Test
    public void testGroupBuyMarket() throws Exception {
        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("lzm");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890001");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(marketProductEntity);
        log.info(marketProductEntity.toString());
        log.info(trialBalanceEntity.toString());
    }
}
