package org.example.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    /**
     * 自增ID
     * 对应字段: id
     */
    private Long id;

    /**
     * 渠道
     * 对应字段: source
     */
    private String source;

    /**
     * 来源
     * 对应字段: channel
     */
    private String channel;

    /**
     * 商品ID
     * 对应字段: goods_id
     */
    private String goodsId;

    /**
     * 商品名称
     * 对应字段: goods_name
     */
    private String goodsName;

    /**
     * 商品价格
     * 对应字段: original_price
     * (使用 BigDecimal 保证金额精度)
     */
    private BigDecimal originalPrice;

    /**
     * 创建时间
     * 对应字段: create_time
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     * 对应字段: update_time
     */
    private LocalDateTime updateTime;
}
