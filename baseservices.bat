@echo off
echo Starting Redis...
start cmd /k "redis-server.exe D:\redis-5\redis.windows.conf"

echo Starting MySQL...
start cmd /k "net start mysql && mysql -uroot -proot"

echo Starting zookeeper...
start cmd /k "I: && cd dubbo-samples && mvnw.cmd exec:java -pl tools\embedded-zookeeper"

echo Services are now running.
