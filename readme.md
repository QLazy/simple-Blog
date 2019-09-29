## 简介

这是一个根据Spring Boot制作的简单博客

## 前端技术

- Thymeleaf
- JQuery

## 后端技术

- Spring Boot
- Mybatis

## 功能

- 等全部写完再来添加

## 资料

- 前端轮子库[Bootstrap](https://www.bootcss.com/)
- GitHub项目树状显示插件[Octotree](http://www.cnplugins.com/devtool/octotree/download.html)
- SQL语句生成[MyBatis Generator](http://www.mybatis.org/generator/index.html)
- 接口测试软件[Pastman](https://www.getpostman.com/downloads/)
- 内嵌Markdown编译器 [eidtor.md](http://editor.md.ipandao.com/)
- [UCloud-sdk](https://github.com/ucloud/ufile-sdk-java)
- 免费的icon[confont](https://www.iconfont.cn/)
- 数据库管理工具[flyway](https://flywaydb.org/)
## 脚本语言
```
//配置完MyBatis Generator后的运行语句,右击pom.xml->Run As->Maven Build
mybatis-generator:generate

//将本地的Oracle驱动导入本地仓库
mvn install:install-file -Dfile=D:\ojdbc6.jar
-DgroupId=com.oracle
-DartifactId=ojdbc6
-Dversion=11.2.0.4.0
-Dpackaging=jar

//lombok安装完后还是无法使用注解，需要将lombok的jar包放入当软件目录下同时在软件目录下的.ini 文件中加入
-Xbootclasspath/a:lombok.jar
-javaagent:lombok.jar

```
部署到服务器
- java -jar -Dspring.profiles.active=production target/mall-0.0.1-SNAPSHOT.jar


---
**2019-8-27更新**

- 增加的index
- 加入了Bootstrap

---
**2019-8-29更新**

- 增加了用户校验
- 利用session保持登录态

---
**2019-8-30更新**

- 使用数据库来储存用户信息
- 使用Lombok来管理实体类
- 使用了mybatis来管理数据库

---
**2019-8-31更新**

- 完善数据库增删改查
- 增加持久化登陆
- 增加Token校验
- 增加Service层，将逻辑部分抽离

---
**2019-9-3更新**

- 增加问题发布功能

---
**2019-9-5更新**

- 增加首页问题列表显示功能

---
**2019-9-6更新**

- 为问题列表添加分页

---
**2019-9-7更新**

- 添加用户个人问题列表页面

---
**2019-9-8更新**

- 增加拦截器校验用户登录状态
- 增加个人问题详情页面

---
**2019-9-9更新**

- 增加个人问题更新功能

---
**2019-9-10更新**

- 使用MyBatis Generator接管SQL语句

---
**2019-9-11更新**

- 修改WhitePage error显示

---
**2019-9-12更新**

- 增加评论功能
- 增加浏览数显示

---
**2019-9-14更新**

- 完善评论后端逻辑
- 增加评论数显示

---
**2019-9-18更新**

- 增加前端一级评论显示
