# 依赖的基础镜像
from java:8
# 创建者
MAINTAINER T12
# 将当前目录下的jar复制到容器/目录下
COPY javaee-backend-0.0.1-SNAPSHOT.jar /javaee-backend-0.0.1-SNAPSHOT.jar
# 指定启动时运行java -jar 命令
ENTRYPOINT ["java", "-jar","/JavaEE-backend-0.0.1-SNAPSHOT.jar"]
