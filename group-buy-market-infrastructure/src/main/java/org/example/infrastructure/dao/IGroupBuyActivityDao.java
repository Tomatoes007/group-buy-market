package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.dao.po.GroupBuyActivity;

import java.util.List;

@Mapper
public interface IGroupBuyActivityDao {
    List<GroupBuyActivity> queryAll();
    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivity);
}
