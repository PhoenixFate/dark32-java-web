# JAVA 黑马32期代码

## 单独打包某个子项目
mvn clean package -pl 父级模块名/子模块名 -am
> 参数说明：
>> - am --also-make 同时构建所列模块的依赖模块；
>> - amd -also-make-dependents 同时构建依赖于所列模块的模块；
>> - pl --projects 构建制定的模块，模块间用逗号分隔；
>> - rf -resume-from 从指定的模块恢复反应堆。

## 跳多单元测试
-Dmaven.test.skip=true 

## 不同环境参数
-Pprod
-Ptest
-Pdev
