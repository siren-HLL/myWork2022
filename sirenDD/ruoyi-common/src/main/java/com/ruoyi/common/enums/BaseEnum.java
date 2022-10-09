package com.ruoyi.common.enums;

/**
 * 枚举基类. 所有的枚举, 必须实现该接口.
 * @author pp
 */
public interface BaseEnum<E extends Enum<?>, T> {
    /**值.*/
    T getValue();
    /** 显示值.  */
    String getDisplayName();
    /** 根据value获取枚举. */
    E getEnum(T value);
}