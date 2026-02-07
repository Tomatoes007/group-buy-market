package org.example.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotifyTaskHTTPEnumVO {
    SUCCESS("success", "成功"),
    ERROR("error", "失败"),
    NULL(null, "空执行");
    private final String code;

    private final String info;
}