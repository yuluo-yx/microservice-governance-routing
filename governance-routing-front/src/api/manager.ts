import axios from "axios";

const service = axios.create({
    baseURL: '/api',
    timeout: 5000
})

// 添加响应拦截器
service.interceptors.response.use((response) => {
    //这里的response就是请求接口以后回调的正确数据
    // console.log(response.data)
    return response;
}, function (error) {
    //这里的error就是请求接口以后回调的错误数据
    // elError(error.response.data || '请求失败')
    return Promise.reject(error.response);
});

// 添加请求拦截器
service.interceptors.request.use((config) => {
    // 如果请求中带有id参数，自动添加 header: {tag=v2}
    let url = String(config.url)
    let idx = Number(url?.lastIndexOf("?"))
    let params = String(config.url?.substring(idx, url.length))
    if (params === "?id=11") {
        config.headers.tag="v2"
    }
    console.log(config);
    return config;
}, function (error) {
    //对请求错误做些什么 
    return Promise.reject(error);
});

export default service