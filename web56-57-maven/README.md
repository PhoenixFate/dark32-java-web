## maven常用指令
### 项目一键构建
1. mvn tomcat:run
```
ps: maven中默认tomcat:run是tomcat6，tomcat6不支持jdk8，
所以需要安装tomcat7-maven插件：
 <plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <port>8089</port>
        <path>/</path>
        <uriEncoding>UTF-8</uriEncoding>
        <finalName>tomcatTest</finalName>
        <server>tomcat</server>
    </configuration>
</plugin>
然后: mvn tomcat7:run
```

### 生命周期命令
1. 清零编译的文件
> mvn clean
2. 编译
> mvn compile
3. 编译并运行test目录
> mvn test (会自动执行compile)
4. 打包
> mvn package (会自动执行compile+test)
5. 把项目发布到本地仓库
> mvn install (会自动执行compile+test+package)

### 不同的mvn命令可以同时进行
- mvn clean package

### jar包依赖范围
- compile \
  编译时需要、测试时需要，运行时需要，打包时需要

- provided \
  编译时需要、测试时需要；运行时不需要，打包时不需要

- runtime \
  编译时不需要、测试时需要、运行时需要、打包时需要
  
- test \
  编译时不需要、测试时需要、运行时不需要，打包时不需要