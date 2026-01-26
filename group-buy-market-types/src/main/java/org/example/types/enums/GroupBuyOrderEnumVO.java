package org.example.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author xt-code
 * @Description:
 * @Create 2025/5/10 22:53
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum GroupBuyOrderEnumVO {
    PROGRESS(0,"进行中"),
    COMPLETE(1,"已完成"),
    FAIL(2,"失败"),
    ;

    private Integer code;
    private String info;

    public static GroupBuyOrderEnumVO valueOf(Integer code) {
        switch (code) {
            case 0:
                return PROGRESS;
            case 1:
                return COMPLETE;
            case 2:
                return FAIL;
        }

        //都不存在，就抛一个异常
        throw new RuntimeException("err code not exist!");
    }

}
