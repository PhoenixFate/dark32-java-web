# JAVA WEB 32期相关代码

## 单独打包某个子项目

mvn clean package -pl 父级模块名/子模块名 -am
> 参数说明：
>> - am --also-make 同时构建所列模块的依赖模块；
>> - amd -also-make-dependents 同时构建依赖于所列模块的模块；
>> - pl --projects 构建制定的模块，模块间用逗号分隔；
>> - rf -resume-from 从指定的模块恢复反应堆。

## tomcat 控制台中文乱码
添加 vm 参数 -Dfile.encoding=UTF-8


## 跳多单元测试

-DskipTests

## 不同环境参数

-Pprod -Ptest -Pdev

### IDEA点击download source 报错：

```
IDEA点击download source 报错：
Caused by: java.rmi.ConnectException: Connection refused to host: 127.0.0.1
```
> 解决方案： 删除对应 project 目录的 .idea文件夹，然后在IDEA中的maven，重新reload一下再点击 Download sources 接口。
