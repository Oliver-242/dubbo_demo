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
