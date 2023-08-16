### preliminary works
#### ***zookeeper注册中心***
```
git clone --depth=1 --branch master git@github.com:apache/dubbo-samples.git
```
```
Windows:
./mvnw.cmd clean compile exec:java -pl tools/embedded-zookeeper

Linux / MacOS:
./mvnw clean compile exec:java -pl tools/embedded-zookeeper

Docker:
docker run --name some-zookeeper -p 2181:2181 --restart always -d zookeeper
```
clone仓库后根据os三选一执行，即可建立注册中心
#### ***mysql***
版本8.0+，数据库名表名根据代码自行匹配

#### ***Test Program***
![image](https://github.com/Oliver-242/dubbo_demo/assets/74057449/54f4df2e-185f-455b-ab05-eec465e5ca76)
![image](https://github.com/Oliver-242/dubbo_demo/assets/74057449/fdd8459a-603a-430a-afbb-8b0c2fad8389)
![image](https://github.com/Oliver-242/dubbo_demo/assets/74057449/302e57f4-6754-4ee0-aa20-b4b86ac21bce)
