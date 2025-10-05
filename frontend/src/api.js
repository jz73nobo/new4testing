// 作用：引入 HTTP 客户端库
// 为什么用axios:功能完整(拦截器 取消请求 自动JSON转换;浏览器和 Node.js 通用;比原生 fetch 更易用
import axios from 'axios';

// 创建自定义的 axios 实例，与全局 axios 隔离
const api = axios.create({

    // Vite 规定，只有以 VITE_ 开头的变量才会暴露给前端;|| '/api'：回退机制。就是通过Vite proxy转发到后端
    baseURL: 'http://localhost:8080', // 指向Spring Boot
});

// 让其他模块可以导入并使用这个配置好的API客户端
export default api;