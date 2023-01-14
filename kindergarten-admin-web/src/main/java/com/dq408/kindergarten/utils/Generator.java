package com.dq408.kindergarten.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class Generator {

    public static void main(String[] args) {
        //1.获取代码生成器的对象
        AutoGenerator autoGenerator = new AutoGenerator();
        //2.设置数据库相关配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/kindergarten?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        autoGenerator.setDataSource(dataSource);

        //3.设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //设置生成完毕后是否打开生成代码所在的目录
        globalConfig.setOutputDir("");
        globalConfig.setOpen(false);
        //设置作者
        globalConfig.setAuthor("XieJinHong");
        //设置是否覆盖原始生成的文件
        globalConfig.setFileOverride(true);
        //设置数据层接口名，%s 为占位符，指代模块名称
        globalConfig.setMapperName("%sDao");
        //设置Id生成策略
        globalConfig.setIdType(IdType.ASSIGN_ID);
        autoGenerator.setGlobalConfig(globalConfig);

        //4.设置包名相关配置
        PackageConfig packageInfo = new PackageConfig();
        //设置生成的包名，与代码所在位置不冲突，二者叠加组成完整路径
        packageInfo.setParent("com.kindergarten");
        //设置实体类包名
        packageInfo.setEntity("domain");
        packageInfo.setMapper("dao");
        //设置数据层包名
        autoGenerator.setPackageInfo(packageInfo);

        //5.策略设置
//        StrategyConfig strategyConfig = new StrategyConfig();
        //设置当前需要生成的表名，参数为可变参数,不写则生成整个数据库的表代码
//        strategyConfig.setInclude("user");
        // 设置数据表的前缀名称，块名 = 数据库表名 _ 前名,如果有该前缀,生成代码时会去去除
//        strategyConfig.setTablePrefix("tbl_");
        // 设置是否启用Rest风格
//        strategyConfig.setRestControllerStyle(true);
        // 设置乐观锁字段名
//        strategyConfig.setVersionFieldName("version");
        // 设置逻辑删除字段名
//        strategyConfig.setLogicDeleteFieldName("deleted");
        // 设置是否启用Lombok
//        strategyConfig.setEntityLombokModel(true);
//        autoGenerator.setStrategy(strategyConfig);
        //6.执行生成操作
        autoGenerator.execute();
    }
}
