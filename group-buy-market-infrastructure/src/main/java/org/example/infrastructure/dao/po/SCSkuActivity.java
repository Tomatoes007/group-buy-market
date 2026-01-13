package org.example.infrastructure.dao.po;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author xt-code
 * @Description:
 * @Create 2025/5/7 19:14
 */
/**
 * 商品活动关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivity implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 渠道
     */
    private String source;

    /**
     * 来源
     */
    private String channel;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}