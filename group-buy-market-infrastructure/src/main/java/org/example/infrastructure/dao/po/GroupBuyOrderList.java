package org.example.infrastructure.dao.po;

import org.example.infrastructure.dao.po.base.Page;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author xt-code
 * @Description:
 * @Create 2025/5/9 17:34
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupBuyOrderList extends Page implements Serializable {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 拼单组队ID
     */
    private String teamId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 渠道
     */
    private String source;

    /**
     * 来源
     */
    private String channel;

    /**
     * 原始价格
     */
    private BigDecimal originalPrice;

    /**
     * 折扣金额
     */
    private BigDecimal deductionPrice;

    /**
     * 状态；0初始锁定、1消费完成
     */
    private Integer status;

    /**
     * 外部交易单号-确保外部调用唯一幂等
     */
    private String outTradeNo;
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