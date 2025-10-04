// React库,处理副作用(数据获取 DOM操作 订阅等),状态管理
import React, { useEffect, useState} from "react";

// api.js文件配置的HTTP客户端
import api from './api';

// 定义并导出一个React函数组件
export default function App() {

    // useState 返回一个数组：[当前值, 设置函数]
    const [items, setItems] = useState([]);  // 需求列表，初始为空数组
    const [title, setTitle] = useState('');  // 输入框内容，初始为空字符串

    // useEffect(回调函数,依赖数组); 作用:组件渲染后执行副作用(如数据获取); []空依赖数组:只在组件首次渲染后执行一次
    useEffect(() => { fetchList(); }, []);

    // 获取数据函数
    // 1. api.get('/requirements')  -> 发送 GET 请求到后端
    // 2. await 等待响应,需要与async一起使用 -> 异步等待
    // 3. res.data 包含后端返回的需求列表
    async function fetchList() {
        const res = await api.get('/api/requirements');
        setItems(res.data);   // 更新状态，触发重新渲染
    }

    // 添加需求函数
    async function add() {
        if (!title) return;   // 防止空标题
        await api.post('api/requirements', { title, description: '', status: 'NEW'});
        setTitle('');   // 清空输入框
        fetchList();   // 重新获取列表（显示新添加的）
    }

    return(
        <div style={{ padding: 20 }}>
            <h2>Requirements</h2>
            {/* 输入框：双向数据绑定 */}
            <input
                value={title}  // 将输入框的值绑定到title状态变量
                onChange={e => setTitle(e.target.value)}  // 当输入内容变化时，更新title状态
                placeholder="title"  // 显示灰色提示文本 "title"
            />

            {/* 按钮：点击触发 add 函数 */}
            <button onClick={add}>Add</button>

            {/* 需求列表渲染 */}
            <ul>
                {/* {items.map(...)}：对 items 数组进行遍历
                i => <li ...>：箭头函数，为每个数组元素生成列表项
                key={i.id}：React 要求的唯一标识，用于优化渲染
                {i.title} — {i.status}：显示需求的标题和状态，中间用破折号连接 */}
                {items.map(i => <li key={i.id}>{i.title} - {i.status}</li>)}
            </ul>
        </div>
    );
}