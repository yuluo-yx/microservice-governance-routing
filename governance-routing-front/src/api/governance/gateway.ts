import { get } from '../axios.ts'

// gateway 发布规则
export const gatewayPublishRule = () => get('http://localhost:19098/addRule', null)

// gateway 更新规则
export const gatewayUpdateRule = () => get('http://localhost:19098/updateRule', null)

// gateway v1 版本测试
export const gatewayV1Request = () => get('http://localhost:19098/routing-service-provider/test-a1', null)

// gateway v2 版本测试
export const gatewayV2Request = () => get('http://localhost:19098/routing-service-provider/test-a1?id=11', null)

// gateway 获取服务列表
export const gatewayGetServerList = () => get('http://localhost:19098/services', null)

// zuul 发布规则
export const zuulAddRule = () => get('http://localhost:19099/addRule', null)

// zuul 更新规则
export const zuulUpdateRule = () => get('http://localhost:19099/updateRule', null)

// zuul v1 版本测试
export const zuulV1Request = () => get('http://localhost:19099/routing-service-provider/test-a1', null)

// zuul v2 版本测试
export const zuulV2Request = () => get('http://localhost:19099/routing-service-provider/test-a1?id=11', null)
