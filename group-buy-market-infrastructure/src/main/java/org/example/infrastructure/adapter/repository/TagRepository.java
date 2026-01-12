package org.example.infrastructure.adapter.repository;

import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import org.example.domain.tag.adapter.repository.ITagRepository;
import org.example.domain.tag.model.entity.CrowdTagsJobEntity;
import org.example.infrastructure.dao.ICrowdTagsDao;
import org.example.infrastructure.dao.ICrowdTagsDetailDao;
import org.example.infrastructure.dao.ICrowdTagsJob;
import org.example.infrastructure.dao.po.CrowdTags;
import org.example.infrastructure.dao.po.CrowdTagsDetail;
import org.example.infrastructure.dao.po.CrowdTagsJob;
import org.example.infrastructure.redis.IRedisService;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class TagRepository implements ITagRepository {

    @Resource
    private ICrowdTagsDao crowdTagsDao;
    @Resource
    private ICrowdTagsDetailDao crowdTagsDetailDao;
    @Resource
    private ICrowdTagsJob crowdTagsJobDao;

    @Resource
    private IRedisService redisService;

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {
        CrowdTagsDetail crowdTagsDetailReq = new CrowdTagsDetail();
        crowdTagsDetailReq.setTagId(tagId);
        crowdTagsDetailReq.setUserId(userId);
        try {
            crowdTagsDetailDao.addCrowdTagsUserId(crowdTagsDetailReq);
            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(redisService.getIndexFromUserId(userId));
        }catch (DuplicateKeyException duplicateKeyException){
            //igore
        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int size) {
        CrowdTags crowdTagsReq = new CrowdTags();
        crowdTagsReq.setTagId(tagId);
        crowdTagsReq.setStatistics(size);

        crowdTagsDao.updateCrowdTagsStatistics(crowdTagsReq);

    }

    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {
        CrowdTagsJob crowdTagsJobReq = new CrowdTagsJob();
        crowdTagsJobReq.setTagId(tagId);
        crowdTagsJobReq.setBatchId(batchId);
        CrowdTagsJob crowdTagsJobRes = crowdTagsJobDao.queryCrowdTagsJob(crowdTagsJobReq);
        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJobRes.getTagType())
                .tagRule(crowdTagsJobRes.getTagRule())
                .statStartTime(crowdTagsJobRes.getStatStartTime())
                .statEndTime(crowdTagsJobRes.getStatEndTime())
                .build();
    }
}
