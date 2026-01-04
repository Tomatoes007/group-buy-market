package org.example.test;


import lombok.extern.slf4j.Slf4j;
import org.example.infrastructure.dao.IGroupBuyActivityDao;
import org.example.infrastructure.dao.IGroupBuyDiscountDao;
import org.example.infrastructure.dao.po.GroupBuyActivity;
import org.example.infrastructure.dao.po.GroupBuyDiscount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstTest {

    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Test
    public void test() {
        log.info("测试完成");
    }

    @Test
    public void test2() {
        List<GroupBuyActivity> list1=groupBuyActivityDao.queryAll();
        log.info(list1.toString());
        List<GroupBuyDiscount> list2=groupBuyDiscountDao.queryAll();
        log.info(list2.toString());
    }

}