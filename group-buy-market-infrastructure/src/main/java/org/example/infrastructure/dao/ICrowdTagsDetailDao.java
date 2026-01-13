package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.dao.po.CrowdTagsDetail;


@Mapper
public interface ICrowdTagsDetailDao {

    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);

    CrowdTagsDetail queryCrowdTagsDetailByTagidAndUserid(CrowdTagsDetail crowdTagsDetailReq);

    void updateCrowdTagsDetailTime(CrowdTagsDetail crowdTagsDetailReq);
}
