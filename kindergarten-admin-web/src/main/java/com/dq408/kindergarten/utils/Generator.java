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
        globalConfig.setOutputDir("D:\\BC\\JAVA\\SourceCode\\Project\\翻斗幼儿园数字化信息管理平台2.0\\后端API\\kindergarten\\kindergarten-admin-web\\src\\main\\java\\com\\dq408\\kindergarten");
        //设置生成完毕后是否打开生成代码所在的目录
        globalConfig.setOpen(true);
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

        autoGenerator.execute();
    }
}
