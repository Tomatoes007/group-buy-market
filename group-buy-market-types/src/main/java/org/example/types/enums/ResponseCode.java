package org.example.types.enums;

import javafx.scene.input.KeyCodeCombination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    DB_EXCEPTION("00001","数据库操作异常"),
    E0001("E0001", "不存在对应折扣计算方式"),
    E0002("E0002", "此商品没有配置任何活动"),
    E0003("E0003", "活动降级拦截"),
    E0004("E0004", "活动切量拦截"),
    E0005("E0005", "拼团组队失败，记录更新为0"),
    E0006("E0006", "拼团组队成功，锁单量已完成"),
    INDEX_EXCEPTION("EEE", "唯一索引冲突");

    private String code;
    private String info;

}
