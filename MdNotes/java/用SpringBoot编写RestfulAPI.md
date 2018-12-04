# 用SpringBoot编写RestfulAPI

- 一点Maven知识
  - pom.xml文件是maven项目的配置文件
  - 几个常用的maven命令(在pom.xml同级目录下运行)
  - mvn test 编译并运行测试用例
  - mvn spring-boot:run 运行spring-boot
  - mvn package 打包项目
  - mvn 可以和其他命令一起用，例如 mvn clean package

- 热部署 [IDE热部署](https://blog.csdn.net/sinat_25305411/article/details/81030503)
    ```java
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
    ```

- 日志
  ```java
  logging:
    file: target/app.log
    level:
      root: error
      com.hansonwang99.controller: debug
  ```
  - 很明显，将root日志级别设置为ERROR，然后再将cn.devmgr.tutroial包的日志级别设为DEBUG，此即：即先禁止所有再允许个别的 设置方法
  - 将日志输出到某个文件中
  - Spring Boot 使用 Log4j2
    ```java
    <!-- Exclude Spring Boot's Default Logging -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>

    <!-- Add Log4j2 Dependency -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
    ```

- 命令行工具CURL
  ```java
  -v 返回详情信息

  #curl http://127.0.0.1:8080/tvseries/

  #curl -H "Content-Type:application/json" -X POST --data '{"name": "west world", "originRelease": "2016-10-02"}' http://127.0.0.1:8080/tvseries/

  #curl -X DELETE http://127.0.0.1:8080/tvseries/23/

  #curl -H "Content-Type:application/json" -X PUT --data '{"name": "west world", "originRelease": "2016-10-02"}' http://127.0.0.1:8080/tvseries/33/
  ```
