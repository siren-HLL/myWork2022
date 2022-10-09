package com.ruoyi.common.utils.uuid;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * ID生成器工具类
 * 
 * @author ruoyi
 */
public class IdUtils
{
    //hutool，避免不同的Snowflake对象创建的ID可能会有重复
    private static Snowflake snowflake = IdUtil.getSnowflake();

    /**
     * 生成long 类型的ID
     * @return
     */
    public static Long getHutoolId() {
        return snowflake.nextId();
    }

    /**
     * 生成String 类型的ID
     * @return
     */
    public static String getHutoolIdStr() {
        return snowflake.nextIdStr();
    }


    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }
}
