package org.example.infrastructure.dao.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotifyTask{
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 拼单组队ID
     */
    private String teamId;

    /**
     * 回调接口
     */
    private String notifyUrl;

    /**
     * 回调次数
     */
    private Integer notifyCount;

    /**
     * 回调状态【0初始、1完成、2重试、3失败】
     */
    private Integer notifyStatus;

    /**
     * 参数对象
     */
    private String parameterJson;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}