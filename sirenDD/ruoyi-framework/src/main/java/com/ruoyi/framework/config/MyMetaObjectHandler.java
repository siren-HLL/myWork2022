package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 自动添加实体创建时间和更新时间
 * @Author : TuBTu
 * @create 2022/7/8/008 17:59
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入时间&插入人");
        this.setFieldValByName("createTime", DateUtils.getNowDate(),metaObject);
        this.setFieldValByName("updateTime", DateUtils.getNowDate(),metaObject);
        this.setFieldValByName("createBy", SecurityUtils.getLoginUser().getUsername(),metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getLoginUser().getUsername(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新时间");
        this.setFieldValByName("updateTime", DateUtils.getNowDate(),metaObject);
//        this.setFieldValByName("updateBy", SecurityUtils.getLoginUser().getUsername(),metaObject);
    }
}
