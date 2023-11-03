#!/bin/sh

example_path="governance-routing-examples/"
gateway_example_project_path=${example_path}"gateway-consumer-example/target"
gateway_example_docker_path="app/gateway-consumer-example/gateway-consumer-example"
zuul_example_project_path=${example_path}"zuul-consumer-example/target"
zuul_example_docker_path="app/gateway-consumer-example/zuul-consumer-example"
feign_example_project_path=${example_path}"web-client-consumer-example/openfeign-consumer-example/target"
feign_example_docker_path="app/webClient-consumer-example/feign-consumer-example"
rest_example_project_path=${example_path}"web-client-consumer-example/restTemplate-consumer-example/target"
rest_example_docker_path="app/webClient-consumer-example/rest-consumer-example"
reactive_example_project_path=${example_path}"web-client-consumer-example/webClient-consumer-example/target"
reactive_example_docker_path="app/webClient-consumer-example/reactive-consumer-example"
provider_example_project_path=${example_path}"routing-service-provider-example/target"
provider_example_docker_path="app/service-provider-example"

cp ../../../${provider_example_project_path}/routing-service-provider-example-*.jar ./${provider_example_docker_path}/app.jar
cp ../../../${gateway_example_project_path}/gateway-consumer-example-*.jar ./${gateway_example_docker_path}/app.jar
cp ../../../${zuul_example_project_path}/zuul-consumer-example-*.jar ./${zuul_example_docker_path}/app.jar
cp ../../../${feign_example_project_path}/openfeign-consumer-example-*.jar ./${feign_example_docker_path}/app.jar
cp ../../../${rest_example_project_path}/restTemplate-consumer-example-*.jar ./${rest_example_docker_path}/app.jar
cp ../../../${reactive_example_project_path}/webClient-consumer-example-*.jar ./${reactive_example_docker_path}/app.jar
