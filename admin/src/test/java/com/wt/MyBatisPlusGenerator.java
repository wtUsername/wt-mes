/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers, if any.
 */

package com.wt;

/**
 * <p> </p>
 *
 * @author wt
 * @version 1.0
 * @since JDK 1.7
 */


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

import com.jtech.marble.util.text.StrUtil;

/**
 * mybatis-plus代码生成器(用于生成entity)<br>
 *
 * @author jiuhua.xu
 */
public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        String targetPath = "e:\\model\\jps";
//        String targetPath = "C:\\Users\\Administrator\\work\\entitys";
//        String targetPath = "/Users/jack/project";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(targetPath);//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("wt");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("dev");
        dsc.setPassword("dev123456");
        dsc.setUrl("jdbc:mysql://localhost:3306/wt-pro?useUnicode=true&characterEncoding=utf-8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"b_base_"});//去除表前缀

        // 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        mpg.setStrategy(strategy);

        // 此处可以修改为您的表
//        String[] incole = new String[]{"bas_company"};
//        strategy.setInclude(incole);

        strategy.setLogicDeleteFieldName("delete_flag");

        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        strategy.setExclude(new String[]{"act_evt_log","act_ge_bytearray","act_ge_property",
//                "act_hi_actinst","act_hi_attachment","act_hi_comment","act_hi_detail",
//                "act_hi_identitylink","act_hi_procinst","act_hi_taskinst","act_hi_varinst",
//                "act_id_group","act_id_info","act_id_membership","act_id_user","act_procdef_info",
//                "act_re_deployment","act_re_model","act_re_procdef","act_ru_deadletter_job",
//                "act_ru_event_subscr","act_ru_execution","act_ru_identitylink","act_ru_job",
//                "act_ru_suspended_job","act_ru_task","act_ru_timer_job","act_ru_variable"});
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);

        String pkg = "com.wt.sys.{}";

        pc.setEntity(StrUtil.format(pkg, "domain"));
        pc.setMapper(StrUtil.format(pkg, "dao"));
        pc.setXml(StrUtil.format(pkg, "mapper"));
        pc.setService(StrUtil.format(pkg, "service"));
        pc.setServiceImpl(StrUtil.format(pkg, "service.serviceImpl"));
        pc.setController(StrUtil.format(pkg, "controller"));
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}
