package org.example.domain.tag.adapter.repository;

import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import org.example.domain.tag.model.entity.CrowdTagsJobEntity;

import java.util.List;

public interface ITagRepository {
    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int size);

    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);
}
