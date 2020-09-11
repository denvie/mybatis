## 介绍

Mybatis training, such as common operation, paging, Mybatis Plus, generator etc.



## 核心模块

* #### **mybatis-generator**

基于[mybatis-generator](http://mybatis.org/generator/)实现根据数据库表自动生成对应的Entity、Mapper、Service、ServiceImpl、Controller、映射文件，通过自定义**CommentGenerator**以及**PluginAdapter**自动生成Java文件的版权信息、类注释、属性注释、方法注释等。

* #### **mybatis-plus-generator**

基于[mybatis-plus-generator](https://github.com/baomidou/mybatis-plus)实现根据数据库表自动生成对应的Entity、Mapper、Service、ServiceImpl、Controller等，支持通过配置文件定制多个模块的代码生成策略。配置类参考**MpGeneratorConfig**，配置文件参考**generatorConfig.yml**，示例如下：

```yaml
# 基础配置
basic:
  copyright: Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
  author: Denvie
  fileOverride: false
  open: false
  enableCache: true
  baseResultMap: true
  baseColumnList: true
  idType: ASSIGN_ID
  swagger2: false
  mapperSuffix: Mapper
  parentPackage: cn.denvie.mybatis.plus
  entityPackage: domain.entity
  mapperPackage: mapper
  servicePackage: service
  serviceImplPackage: service.impl
  controllerPackage: controller

# JDBC配置
jdbc:
  dbType: mysql
  url: jdbc:mysql://host.docker.internal:3306/mybatis-plus?serverTimezone=GMT%2B8&useSSL=true&useUnicode=true&characterEncoding=UTF-8
  driverName: com.mysql.cj.jdbc.Driver
  username: root
  password: root

# 表策略配置
table:
  entityLombokModel: true
  versionFieldName: lockVersion
  logicDeleteFieldName: deleted
  entityTableFieldAnnotationEnable: true
  restControllerStyle: true
  controllerMappingHyphenStyle: true
  entitySerialVersionUID: false
  superEntityClass: cn.denvie.mybatis.plus.base.BaseEntity
  superEntityColumns: gmt_create, gmt_modified, lock_version, deleted
  tableFillVoList:
    - fieldName: gmtCreate
      fieldFill: INSERT
    - fieldName: gmtModified
      fieldFill: UPDATE

# 业务模块配置
modules:
  - moduleName: app
    modulePath: mybatis-plus-generator
    include: tbl_user, tbl_student, tbl_class
    tablePrefix: tbl_
```



## 参考资料
* MyBatis3 文档：[https://mybatis.org/mybatis-3/index.html](https://mybatis.org/mybatis-3/index.html)
* MyBatis3 Github：[https://github.com/mybatis/mybatis-3](https://github.com/mybatis/mybatis-3/) 
* MyBatis Generator 文档：[http://mybatis.org/generator](http://mybatis.org/generator/)
* MyBatis Generator Github：[https://github.com/mybatis/generator](https://github.com/mybatis/generator)
* MyBatis Spring Boot Starter Github: [https://github.com/mybatis/spring-boot-starter](https://github.com/mybatis/spring-boot-starter)
* PageHelper Github：[https://github.com/pagehelper/Mybatis-PageHelper](https://github.com/pagehelper/Mybatis-PageHelper)
* PageHelper Spring Boot Starter Github: [https://github.com/pagehelper/pagehelper-spring-boot](https://github.com/pagehelper/pagehelper-spring-boot)
* MyBatis Plus 文档：[https://baomidou.com/guide](https://baomidou.com/guide/)
* MyBatis Plus Github: [https://github.com/baomidou/mybatis-plus](https://github.com/baomidou/mybatis-plus)