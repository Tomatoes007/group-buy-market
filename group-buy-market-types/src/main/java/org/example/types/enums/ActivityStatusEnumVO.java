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
public enum ActivityStatusEnumVO {
    CREATE(0, "创建"),
    EFFECTIVE(1, "生效"),
    OVERDUE(2, "过期"),
    ABANDONED(3, "废弃"),
    ;

    private Integer code;
    private String info;

    public static ActivityStatusEnumVO valueOf(Integer code) {
        switch (code) {
            case 0:
                return CREATE;
            case 1:
                return EFFECTIVE;
            case 2:
                return OVERDUE;
            case 3:
                return ABANDONED;
        }

        //都不存在，就抛一个异常
        throw new RuntimeException("err code not exist!");
    }
}