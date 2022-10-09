# ruoyi
## 2022-07-11
>曾，余

1.整合Mybatis Plus

    建议使用MP提供的添加和修改方法
    代码生成后 主键id 需要手动添加tableId
    配置类 ruoyi-framework/src/main/java/com/ruoyi/framework/config/MyBatisPlusConfig.java

2.整合A.C.Table

    实体和表字段自动映射更改

3.BaseEntity 添加数据库字段映射

4.代码生成器更改：

    domain.java.vm
    service.java.vm
    serviceImpl.java.vm
    mapper.java.vm
    VelocityUtils.java

5.新增 ruoyi-framework/src/main/java/com/ruoyi/framework/config/MyMetaObjectHandler.java

    插入和更新操作自动更改时间和操作人

6.新增 ruoyi-admin/src/main/java/com/ruoyi/web/core/config/TunnelConfig.java

    新增模块jinhuo-tunnel的配置类（涉及pom.xml,application.yml)

7.修改sys_user，自增主键改为雪花算法;sys_config初始密码改为"a123456";修改sys_dept，自增主键改为雪花算法

    涉及数据库更改(sql/ry_20220625.sql);

8.新增 ruoyi-framework/src/main/java/com/ruoyi/framework/config/MyBatisPlusConfig.java

    Mybatis-Plus 配置类

## 2022-07-12
>余

1.增加雪花算法id工具  IdUtils

2.增加配置类 ruoyi-framework/src/main/java/com/ruoyi/framework/config/LongToStringJsonConfig.java

3.修改底层id生成







