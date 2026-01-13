package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.dao.po.SCSkuActivity;

@Mapper
public interface ISCSkuActivityDao {
    SCSkuActivity querySCSkuActivityByGoodsId(SCSkuActivity scSkuActivity);
}
