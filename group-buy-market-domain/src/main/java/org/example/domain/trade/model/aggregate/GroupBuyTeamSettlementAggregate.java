package org.example.domain.trade.model.aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.trade.model.entity.GroupBuyTeamEntity;
import org.example.domain.trade.model.entity.TradePaySuccessEntity;
import org.example.domain.trade.model.entity.UserEntity;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyTeamSettlementAggregate {
    /**
     * 用户实体对象
     */
    private UserEntity userEntity;
    /**
     * 拼团组队实体对象
     */
    private GroupBuyTeamEntity groupBuyTeamEntity;
    /**
     * 交易支付订单实体对象
     */
    private TradePaySuccessEntity tradePaySuccessEntity;
}
