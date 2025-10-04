// Vite的配置函数，提供类型提示和自动补全
import { defineConfig } from 'vite'

// Vite的React插件，支持JSX、热更新等React特性
import react from '@vitejs/plugin-react'

export default defineConfig({   // 导出 Vite 配置对象
    plugins: [react()],  // 启用React插件.JSX/TSX 文件编译;热模块替换
    server: {
        port: 5173,  // 前端运行在：http://localhost:5173
        proxy: {
            '/api': 'http://localhost:8080'  // 后端运行在：http://localhost:8080
        }
    }
})