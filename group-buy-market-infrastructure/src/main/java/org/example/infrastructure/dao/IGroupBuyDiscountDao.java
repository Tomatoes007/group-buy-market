package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.dao.po.GroupBuyDiscount;

import java.util.List;

@Mapper
public interface IGroupBuyDiscountDao {
    List<GroupBuyDiscount> queryAll();
    GroupBuyDiscount queryGroupBuyDiscountById(String discountId);
}
