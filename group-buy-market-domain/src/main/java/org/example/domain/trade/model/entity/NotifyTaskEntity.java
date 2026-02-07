package org.example.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: xt-code
 * @date: 2025/5/14 10:23
 * @description: 持久化任务
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotifyTaskEntity {
    /**
     * 拼单组队ID
     */
    private String teamId;
    /**
     * 回调类型  MQ / HTTP
     */
    //private String notifyType;

    /**
     * 回调-MQ消息
     */
    //private String notifyMQ;

    /**
     * 回调接口
     */
    private String notifyUrl;
    /**
     * 回调次数
     */
    private Integer notifyCount;
    /**
     * 参数对象
     */
    private String parameterJson;

    public String lockKey() {
        return "notify_job_lock_key_" + this.teamId;
    }
}