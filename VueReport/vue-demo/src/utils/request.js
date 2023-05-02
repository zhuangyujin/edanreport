import axios from 'axios'

// 创建 axios 对象
const instance = axios.create({
    timeout: 5000 // 网络延时
})

// 添加请求拦截器 => 前端给后端的参数【还没到后端响应】
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器 => 后端给前端的数据【后端返回给前端的东西】
instance.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response;
}, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
});

// 最终返回的对象
export default instance
