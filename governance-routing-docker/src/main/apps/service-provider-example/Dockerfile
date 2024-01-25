FROM registry.cn-hangzhou.aliyuncs.com/yuluo-yx/java-base:V20240116 as builder

LABEL author="yuluo" \
	email="1481556636@qq.com"

ARG APP_HOME

VOLUME ${APP_HOME}

WORKDIR ${APP_HOME}

ENTRYPOINT ["sh", "start-label-routing-service-provider.sh"]
