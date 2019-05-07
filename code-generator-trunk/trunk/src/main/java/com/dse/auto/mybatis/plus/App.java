package com.dse.auto.mybatis.plus;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 * <p>
 * 在自动创建时，首先回去数据库中查询：【如果不在同一个SCHEMA中，创建不出来文件】
 * SELECT * FROM ALL_TAB_COMMENTS WHERE OWNER='SZSW' AND TABLE_NAME IN ('DSE_WIU_WC_A') 检查这个表是否在对应的SCHEMA中
 * OWNER ： 数据库的 SCHEMA_NAME
 * TABLE_NAME ：表的名称
 */
public class App {

    /**
     * 项目名称，即ideax项目的项目名称
     */
    private static String projectName = "codegenerator";
    /**
     * 注释中的开发者名称
     */
    private static String author = "yulang";
    /**
     * 数据库表前缀，配制后，生成的pojo dao service将去掉表前缀
     */
    private static String tablePrefix = ""; //
    /**
     * 需要生成代码的表明，注意表明大写
     */
    private static String tableInclude = "user";
    /**
     * 生成代码的包路径配置
     */
    private static String packageName = "com.edwin.java";
    /**
     * 模块名称 packageName + moduleName将组成存放源代码的包路径，如：com.dse.order
     */
    private static String moduleName = "";
    /**
     * 生成的接口是否已"I"开头，如IServiceName
     */
    private static boolean serviceNameStartWithI = false;
    /**
     * 数据库连接
     * jdbc:mysql://localhost:3306/ant?useUnicode=true&useSSL=false&characterEncoding=utf8
     */
    private static String DB_URL ="jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
            // "jdbc:sqlserver://10.100.50.77:1433;DatabaseName=JZPDA";  sqlserver
    /**
     * 数据库SCHEMA
     */
    private static String SCHEMA_NAME = "";
    /**
     * 数据库用户名
     */
    private static String USER_NAME = "root";
    /**
     * 数据库密码
     */
    private static String PASSWORD = "123456";
    /**
     * 数据库类型
     */
    private static DbType dbType = DbType.MYSQL;
    /**
     * 数据库驱动名称
     */
    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
            //"com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /***
     *  配置具体使用的生成代码的项目路径，这样可以方便自动生成到指定的项目路径下，不需要手动去拷贝
     *  比如你项目在 E:\auth2\RESOURCE_SERVER，生成的文件就会自动拷贝到项目文件夹下的 com.dse.web.test 包下
     *  如果不配置，则生成的文件在当前项目中创建文件包结构保存，需要拷贝到其他处
     */
    private static String CUMOSTER_PROJECCT_URL = "G:\\workspace-sts\\201904\\user-server";

    public static void main(String[] args) {
        generateByTables(serviceNameStartWithI, packageName, StringUtils.split(tableInclude));
    }

    /**
     * 根据表自动生成
     *
     * @param serviceNameStartWithI 默认为false
     * @param packageName           包名
     * @param tableNames            表名
     * @author Terry
     */
    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        //自定义配置
        InjectionConfig injectionConfig = getInjectionConfig();
        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig, injectionConfig);
    }

    /**
     * 集成
     *
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig   策略配置
     * @param config           全局变量配置
     * @param packageConfig    包名配置
     * @author Terry
     */
    private static void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig, InjectionConfig injectionConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setCfg(injectionConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
               // .setTemplate(new TemplateConfig().setXml(null).setServiceImpl("/template4me/serviceImpl.java").setController("/template4me/controller.java"))
                .setTemplate(new TemplateConfig().setXml(null).setServiceImpl("/templates/serviceImpl.java").setController("/templates/controller.java"))
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @return PackageConfig 包名配置
     * @author Terry
     */
    private static PackageConfig getPackageConfig(String packageName) {
        PackageConfig pc = new PackageConfig()
                .setParent(packageName)
                .setXml("mapper")
                .setMapper("mapper")
                .setController("controller")
                .setEntity("entity");
        if(!StringUtils.isEmpty(App.moduleName)) {
            pc.setModuleName(App.moduleName);
        }
        return pc;
    }

    /**
     * 全局配置
     *
     * @param serviceNameStartWithI false
     * @return GlobalConfig
     * @author Terry
     */
    private static GlobalConfig getGlobalConfig(boolean serviceNameStartWithI) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setActiveRecord(false)
                .setAuthor(App.author)
                .setControllerName("%sController")
                //设置输出路径
                .setOutputDir(getOutputDir(App.projectName))
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            //设置service名
            globalConfig.setServiceName("%sService");
        }
        globalConfig.setMapperName("%sMapper");
        return globalConfig;
    }

    /**
     * 返回项目路径
     *
     * @param projectName 项目名
     * @return 项目路径
     * @author Terry
     */
    private static String getOutputDir(String projectName) {
        String path = App.class.getClassLoader().getResource("").getPath();
        int index = path.indexOf(projectName);
        // return path.substring(1, index) + projectName + "/src/main/java/";
        return StringUtils.isEmpty(CUMOSTER_PROJECCT_URL) ? path.substring(1, path.length()) + projectName + "/src/main/java/" : CUMOSTER_PROJECCT_URL + "/src/main/java/";
    }

    /**
     * 返回配置文件地址
     *
     * @param projectName 项目名
     * @return 项目路径
     * @author Terry
     */
    private static String getResourcesOutputDir(String projectName) {
        String path = App.class.getClassLoader().getResource("").getPath();
        int index = path.indexOf(projectName);

        //  return path.substring(1, index) + projectName + "/src/main/resources/";
        return StringUtils.isEmpty(CUMOSTER_PROJECCT_URL) ? path.substring(1, path.length()) + projectName + "/src/main/resources/" : CUMOSTER_PROJECCT_URL + "/src/main/resources/";
    }

    /**
     * 策略配置
     *
     * @param tableNames 表名
     * @return StrategyConfig
     * @author Terry
     */
    private static StrategyConfig getStrategyConfig(String... tableNames) {
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                //设置表明前段，设置后将去掉表明前缀生成相关类
                .setTablePrefix(StringUtils.split(App.tablePrefix))
                .setEntityLombokModel(false)
                // 表名、字段名、是否使用下划线命名（默认 false）
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //从数据库表到文件的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //rest服务
                .setRestControllerStyle(true)
                //需要生成的的表名，多个表名传数组
                .setInclude(tableNames);
    }

    /**
     * 配置数据源
     *
     * @return 数据源配置 DataSourceConfig
     * @author Terry
     */
    private static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig().setDbType(App.dbType)
                .setUrl(App.DB_URL)
                .setSchemaName(App.SCHEMA_NAME)
                .setUsername(App.USER_NAME)
                .setPassword(App.PASSWORD)
                .setDriverName(App.DRIVER_NAME);
    }

    /**
     * 根据表自动生成
     *
     * @param packageName 包名
     * @param tableNames  表名
     * @author Terry
     */
    @SuppressWarnings("unused")
    private static void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }

    /**
     * 自定义配置
     *
     * @return
     */
    public static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                System.out.println(">>>"+getResourcesOutputDir(App.projectName));
                return getResourcesOutputDir(App.projectName) + "mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        return cfg;
    }



}
