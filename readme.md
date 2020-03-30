### Database Demo

本測試使用的 Schema與Structure

```sql

# Dump of table book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;

INSERT INTO `book` (`bookid`, `name`, `author`)
VALUES
	(1,'天龍八部','金庸'),
	(2,'鹿鼎記','金庸'),
	(3,'多拉Ａ夢','藤子不二雄');

/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table members
# ------------------------------------------------------------

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `member_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`member_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;

INSERT INTO `members` (`member_id`, `first_name`, `last_name`, `email`)
VALUES
	(1,'Adam','OuYang','adam@example.com'),
	(2,'Brown','Chen','brown@example.com'),
	(3,'Cayla','Liao','cayla@example.com');

/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

# Dump of table department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department`;
-- Create syntax for TABLE 'department'
CREATE TABLE `department` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


# Dump of table employee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employee`;
-- Create syntax for TABLE 'employee'
CREATE TABLE `employee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `gender` int(2) DEFAULT NULL,
  `dId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
```


### Druid 監控平台

阿里巴巴的監控平台

Spring boot 啟動後，可訪問監控後台

（注意，使用 Druid 監控，要停止 spring security 或者將 druid 路由排除在驗證清單）

[http://localhost:4000/druid/login](http://localhost:4000/druid/login)

pom.xml
```xml
		<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid-spring-boot-starter</artifactId>
			<version>1.1.9</version>
		</dependency>
```

application.yml

```yaml
spring:
    datasource:
        druid:
            initial-size: 5
            max-active: 20
            min-idle: 10
            max-wait: 10
            web-stat-filter:
                enable: true
                url-pattern: /*
                execlusions: "*.gif,*.png,*.jpg,*.html,*.js,*.css,*.ico,/druid/*"
            stat-view-servlet:
                enable: true
                url-pattern: /druid/*
                reset-enable: true
                login-username: admin
                login-password: admin
                allow:
                deny:

```

### MyBatis 

pom.xml
```xml

		<dependency>
			<groupId>com.baomidou</groupId>
			<artifactId>mybatis-plus-boot-starter</artifactId>
			<version>3.3.1.tmp</version>
		</dependency>
```


例如， 專案名稱為 ```com.base.basetest```

java/專案/DemoApplication.java 增加 MapperScan ，讓 MyBatis 知道要自動掃瞄哪一個檔案
```java
package com.base.basetest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.base.basetest.mapper") //使用 MapperScan 批次掃描 mapper 資料夾：mybatis 對 mapper 層自動掃瞄
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```

新增 Model ，這裡透過 lombok 來輔助生成 Model
```java
package com.base.basetest.models;

import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Department {

    @Id
    @GeneratedValue
    private Integer id;
    private String departmentName;

}

```

生成 mapper
這裡建立 mapper.DepartmentMapper.java

```java
package com.base.basetest.mapper;

import com.base.basetest.models.Department;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id") //自動生成id，可即時取回目前insert 的 id
    @Insert("insert into department(departmentName) value(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
```

建立 Controller

DepartmentController.java

透過 @Autowired 自動注入 DepartmentMapper 後，在 Controller 進行數據操作

```java
package com.base.basetest.controllers;

import com.base.basetest.mapper.DepartmentMapper;
import com.base.basetest.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    //自動載入 DepartmentMapper
    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department department){
           departmentMapper.insertDept(department);
           return department;
    }
}

```


### MyBatis Generator

MyBatis Generator 是一個可自動生成 model, mapper, service, controller 等 CRUD 架構的自動生成器，以下是設定流程

pom.xml

```xml

		<!--lombok-->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<!--my-batis-plus ORM-->
		<dependency>
			<groupId>com.baomidou</groupId>
			<artifactId>mybatis-plus-boot-starter</artifactId>
			<version>3.3.1.tmp</version>
		</dependency>
		<dependency>
			<groupId>com.baomidou</groupId>
			<artifactId>mybatis-plus-generator</artifactId>
			<version>3.3.1.tmp</version>
		</dependency>
		<dependency>
			<groupId>org.freemarker</groupId>
			<artifactId>freemarker</artifactId>
			<version>2.3.30</version>
		</dependency>

```

application.yml
```yaml
mybatis-plus:
    global-config:
        db-config:
            db-type: mysql
```

/src/main/java/專案/MyBatisPlusCodeGenerator.java

```java
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyBatisPlusCodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("作者名稱");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/資料庫名稱?useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("資料庫帳號");
        dsc.setPassword("資料庫密碼");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.base.basetest");
        mpg.setPackageInfo(pc);

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
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
```

Generator 可直接在  MyBatisPlusCodeGenerator 的 Class 啟動，啟動後，輸入模塊名及表名即可自動生成 CRUD 


### @ConfigurationProperties 取得設定

在 Spring Boot 可以透過 appliction.yml 來設定引用的套件，也可以自定義設定

針對自定義的設定，可以透過 ConfigurationProperties 批量取得設定檔的值，並且支持鬆散綁定（同時支援駝峰及底線格式）。以下隱形說明：

首先，在 pom.xml 引入 configuration-processor ，讓 Spring boot 可以取得提示
```xml

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>

```


配置文件必須要告訴 Spring boot 他是配置文件，可以載入到 component 中
方式有兩種，一個是直接在 configuration 檔案中指定 @Configurate 
或者在 Application 檔案加入 @ConfigurationPropertiesScan (如果在 xxxApplication.java 設定了 ConfigurationScan ，這裡就不用再寫引用這個)

例如：
```java
package com.base.basetest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@MapperScan("com.base.basetest.mapper") //使用 MapperScan 批次掃描 mapper 資料夾：mybatis 對 mapper 層自動掃瞄
@ConfigurationPropertiesScan("com.base.basetest.bean")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
```

將配置文件中的值，映射到組件
透過 @ConfigurationProperties 可以告訴 Spring boot ，這個 Class 裡面所有屬性，都是和配置文件綁定

在這裡，統一將 Configuration 放在 bean 資料夾

例如： bean.MypersonConfiguraties.java
```java
package com.base.basetest.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@Configuration  //如果在 DemoApplication.java 設定了 ConfigurationScan ，這裡就不用再寫引用這個
@ConfigurationProperties(prefix = "myperson")
public class MypersonConfiguraties {
    private String name;
    private int age;
    /**
    * 以上寫完後，透過 cmd+n 自動生成 get, set, toString   （在這裡不贅述）
    **/

}
```

### @Value 取得設定值

正常情況，我們都會使用 @ConfigurateProperity 來取得設定值
但有些情況需要取得單一設定值，就可以透過 @Value 來取得。

@Value 可以注入單一設定值，不支持鬆散綁定

但是可支持 SP 表達式  #{SP}

```xml
<bean class="Person">
<property name="LastName" value="支援三種格式：值/${key}/#{SP}"></property>
</bean>
```

例如：application.properites

```yaml
myperson:
    say: Hello world config
```

```java
@Value("${myperson.say}")
```

例如，在 Controller 中取得單一設定值

```java
package com.base.basetest.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${myperson.say}")
    private String sayHello;

    @RequestMapping("/hello")
    public String hello(){
        return sayHello;
    }
}

```

### @PropertySource & @ImportResource & Spring 推薦用配置類

PropertySource:

在 Spring boot 可以自定義 properties 檔案，並且透過 @PropertySource 來提取，再讓 @ConfigurationProperties 來讀取

例如，在 resources 建立 personal.properties

可以透過以下方式載入

```java
import org.springframework.context.annotation.PropertySource;
@PropertySource(value={"classpath:personal.properties"})
```

ImportResource:

可以加載 xml 配置文件

例如，在 resource 新增 myconfig.xml 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <bean id="MemberService" class="com.base.basetest.services.MemberService"></bean>
</beans>
```

單一檔案，可以透過 ImportResource 進行載入 spring 設定文件

```java
import org.springframework.context.annotation.ImportResource;
@ImportResource(locations = {"classpath:myconfig.xml"})
```

但是這種 ImportResource 方式並不推薦，在 Spring 推薦用配置類別來配置

Spring 推薦用配置類:

例如：

```java
package com.base.basetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 聲明當前是一個配置類別
 */
@Configuration
public class MyConfig {

    //Bean 可將方法返回值，添加到容器中
    @Bean
    public String aaa(){
        System.out.println("在配置類別設定添加組件");
        return "helloworld";
    }
}


```

### Profile 設定環境，properties 與 yml 使用方式

application.properties 是主配置文件

可以針對不同環境設定配置：

1. properties 文件設定方式

開發環境設置 application.dev.properties
正式環境設置 application.prod.properties
測試環境設置 application.test.properties

激活方式，可以在主配置文件，激活讀取 dev 文件
```java
spring.profiles.active=dev
```
2. yml 設定方式

透過 application.yml 設定，主要可透過 ```---``` 來設定區塊
並且透過 ```spring.profiles``` 來定義區塊名稱
用 ```spring.active``` 指定要執行的區塊

例如，底下指定執行 prod 區塊
```yaml

spring:
    profiles:
        active: prod
---

server:
    port: 4000
spring:
    profiles: prod
---

server:
    port: 4001
spring:
    profiles: dev

---

server:
    port: 4002
spring:
    profiles: test

---
```
