package org.example.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.activity.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TrialBalanceEntity {
    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品原价
     */
    private BigDecimal originalPrice;

    /**
     * 优惠金额（或秒杀价）
     */
    private BigDecimal deductionPrice;

    private BigDecimal payPrice;
    /**
     * 目标数量（如秒杀库存）
     */
    private Integer targetCount;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 是否可见
     */
    private Boolean isVisible;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;
    // 省略getter/setter方法
}
