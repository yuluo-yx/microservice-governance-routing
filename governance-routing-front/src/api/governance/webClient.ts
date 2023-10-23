import { get } from '../axios.ts'

// openfeign 发布规则
export const openfeignAddRule = () => get('http://localhost:19095/add', null)

// openfeign 更新规则
export const openfeignUpdateRule = () => get('http://localhost:19095/update', null)

// openfeign v1 版本测试
export const openfeignV1Request = () => get('http://localhost:19095/router-test', null)

// openfeign v2 版本测试
export const openfeignV2Request = () => get('http://localhost:19095/router-test?id=11', null)

// resttemplate 发布规则
export const restTemplateAddRule = () => get('http://localhost:19097/add', null)

// resttemplate 更新规则
export const restTemplateUpdateRule = () => get('http://localhost:19097/update', null)

// resttemplate v1 版本测试
export const restTemplateV1Request = () => get('http://localhost:19097/router-test', null)

// resttemplate v2 版本测试
export const restTemplateV2Request = () => get('http://localhost:19097/router-test?id=11', null)

// webclient 发布规则
export const webClientAddRule = () => get('http://localhost:19096/add', null)

// webclient 更新规则
export const webClientUpdateRule = () => get('http://localhost:19096/update', null)

// webclient v1 版本测试
export const webClientV1Request = () => get('http://localhost:19096/router-test', null)

// webclient v2 版本测试
export const webClientV2Request = () => get('http://localhost:19096/router-test?id=11', null)

// webclient 获取所有服务实例
export const webClientGetServerList = () => get('http://localhost:19096/all-services', null)

// webclient 读取访问节点信息
export const webClientGetServerNode = () => get('http://localhost:19096/nodeInfo', null)
