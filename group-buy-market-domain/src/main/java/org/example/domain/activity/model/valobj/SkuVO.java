package org.example.domain.activity.model.valobj;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuVO {

    private String goodsId;
    private String goodsName;
    private BigDecimal originalPrice;
}
