// 作用：引入 HTTP 客户端库
// 为什么用axios:功能完整(拦截器 取消请求 自动JSON转换;浏览器和 Node.js 通用;比原生 fetch 更易用
import axios from 'axios';

// 创建自定义的 axios 实例，与全局 axios 隔离
const api = axios.create({

    // Vite 规定，只有以 VITE_ 开头的变量才会暴露给前端;|| '/api'：回退机制。就是通过Vite proxy转发到后端
    baseURL: import.meta.env.VITE_API_URL || '/api', // dev时使用proxy(代理)在前端开发中是一个非常实用的概念,它就像一个中间人,帮你在前端和后端服务器之间转发请求。
});

// 注册请求拦截器，在每个请求发送前执行
api.interceptors.request.use(cfg => {  // cfg 是请求配置对象
    
    // 从本地存储获取认证。token:页面刷新后token不丢失; 比sessionStorage生命周期长
    const token = localStorage.getItem('token');
    
    // 自动添加认证头
    if (token) cfg.headers.Authorization = token;
    
    // 必须返回修改后的配置对象
    return cfg;
});

// 让其他模块可以导入并使用这个配置好的API客户端
export default api;