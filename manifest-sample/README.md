Android 空工程示例，对比从 AndroidStudio 创建工程，少去很多依赖配置和模板。**适合像楼主这样强迫症晚期患者**。

## 使用说明

使用`git clone --branch [tags|branches] [git地址]`命令导出代码。

如：

```shel
git clone --branch v0.3.0 https://github.com/flueky/Demo.git
```

## 版本说明

|版本号|说明|
|:-:|:--|
|v0.1.0|最间Android工程目录|
|v0.1.1|添加库依赖配置|
|v0.2.0|添加主题和包名|
|v0.2.1|删除对 lib 库的以来配置|
|v0.2.2|添加最小支持版本|
|v0.2.3|添加详细的 gradle 配置|
|v0.3.0|集成 maven 依赖|


* 注：**示例中使用配置的签名文件信息使用配置文件的方式，未保证签名文件的安全，未上传。需要换成自己的签名文件使用。**

`keystore.properties`文件内容如下：

```properties
keyAlias=android
keyPassword=android
storeFile=../demo.keystore
storePassword=android
```




