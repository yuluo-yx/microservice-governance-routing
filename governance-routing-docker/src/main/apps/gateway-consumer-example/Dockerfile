FROM registry.cn-hangzhou.aliyuncs.com/yuluo-yx/java-base:V20240116 as builder

LABEL author="yuluo" \
	email="1481556636@qq.com"

ARG APP_HOME

VOLUME ${APP_HOME}

WORKDIR ${APP_HOME}

ENTRYPOINT ["java", "-jar", "app.jar", "-Dspring.profiles.active=docker"]