import { get } from '../axios.ts'

// gateway 发布规则
export const gatewayPublishRule = () => get('http://localhost:19098/addRule', null)

// gateway 更新规则
export const gatewayUpdateRule = () => get('http://localhost:19098/updateRule', null)

// gateway v1 版本测试

// gateway v2 版本测试

// gateway 获取服务列表

// zuul 发布规则

// zuul 更新规则

// zuul v1 版本测试

// zuul v2 版本测试


