package org.example.api.dto;

import lombok.Data;

import java.util.List;
@Data
public class NotifyRequestDTO {

    /** 组队ID */
    private String teamId;

    /** 外部单号 */
    private List<String> outTradeNoList;
}