## 普通jsp商城 后管

### 根据命令来打包
```
mvn clean package -Dmaven.test.skip=true -Ptest
mvn clean package -Dmaven.test.skip=true -Pdev
mvn clean package -Dmaven.test.skip=true -Pprod
```

