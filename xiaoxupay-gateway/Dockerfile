# 运行时环境
FROM docker.xxce.cn/xiaoxu-docker/openjdk:17
LABEL authors="Xiaoxu"

WORKDIR /app

# 创建目录
RUN mkdir -p /app/logs

# 复制构建产物
COPY  /target/xiaoxupay-gateway-1.0.jar /app/

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=prod \
    JVM_OPTS="-Xms1024M -Xmx1024M -Xss256K -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -Xlog:gc*:file=/app/logs/gc.log:time,level,tags"

# 暴露端口
EXPOSE ${SERVER_PORT}

# 设置启动命令
CMD java \
    -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
    ${JVM_OPTS} \
    -jar xiaoxupay-gateway-1.0.jar
