package com.ruoyi.web.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Siren 2022/7/8 15:17
 * 新增一个config类，扫描com.siren.dudao,将siren-dudao模块下的所有组件加入spring管理
 */
@Configuration
@ComponentScan(basePackages = "com.siren.dudao")
public class DudaoConfig {
}
