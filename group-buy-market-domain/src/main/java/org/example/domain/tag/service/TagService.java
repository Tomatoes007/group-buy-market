package org.example.domain.tag.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.tag.adapter.repository.ITagRepository;
import org.example.domain.tag.model.entity.CrowdTagsJobEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TagService implements ITagService {

    @Resource
    private ITagRepository tagRepository;

    @Override
    public void execTagBatchJob(String tagId, String batchId) {
        CrowdTagsJobEntity crowdTagsJobEntity = tagRepository.queryCrowdTagsJobEntity(tagId,batchId);

        List<String> userIdList = new ArrayList<String>() {
            {
                add("xiaofuge");
                add("liergou");
                add("lzm");
                add("xgf");
            }
        };

        for(String userId : userIdList) {
            tagRepository.addCrowdTagsUserId(tagId, userId);
            }

        tagRepository.updateCrowdTagsStatistics(tagId,userIdList.size());
    }
}
