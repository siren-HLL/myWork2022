package com.ruoyi.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Siren 2022/9/26 11:18
 * 数据包围方式；
 */
public enum IncludingBeforeAfterEnum implements BaseEnum<IncludingBeforeAfterEnum, String>{
    INCLUDING_NO("101", "前后都不包括"),
    INCLUDING_BEFORE("102", "只包括前"),
    INCLUDING_AFTER("103", "只包括后"),
    INCLUDING_BEFORE_AFTER("104", "前后都包括");
    private static final Map<String, IncludingBeforeAfterEnum> valueMap = new HashMap<>();
    static {
        for (IncludingBeforeAfterEnum e : IncludingBeforeAfterEnum.values()) {
            valueMap.put(e.value, e);
        }
    }
    private final String value;
    private final String displayName;

    IncludingBeforeAfterEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }
    public static IncludingBeforeAfterEnum get(String value) {
        return valueMap.get(value);
    }
    public static String getDisplayName(String value) {
        IncludingBeforeAfterEnum k= valueMap.get(value);
        if(k!=null) return k.getDisplayName();
        return "";
    }

    @Override
    public String getValue() {
        return value;
    }
    @Override
    public String getDisplayName() {
        return displayName;
    }
    @Override
    public IncludingBeforeAfterEnum getEnum(String value) {
        return valueMap.get(value);
    }
}
