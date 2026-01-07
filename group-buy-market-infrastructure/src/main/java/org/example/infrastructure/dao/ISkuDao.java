package org.example.infrastructure.dao;


import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.dao.po.Sku;

@Mapper
public interface ISkuDao {
     Sku querySkuByGoodsId(String id);
}
