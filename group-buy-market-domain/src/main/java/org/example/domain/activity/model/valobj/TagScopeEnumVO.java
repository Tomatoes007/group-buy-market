package org.example.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TagScopeEnumVO {
    VISIBLE(true,false,"是否可看见拼团"),
    ENABLE(true,false,"是否可参见拼团"),
    ;
    private boolean allow;
    private boolean refuse;
    private String description;
}
