package org.example.domain.tag.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrowdTagsJobEntity {

    private Integer tagType;
    private String tagRule;
    private Date statStartTime;
    private Date statEndTime;

}
