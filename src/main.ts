import { createApp } from 'vue'
//import './style.css'
import App from './App.vue'
// 1. 引入你需要的组件
import vant from 'vant';
// 2. 引入组件样式
import 'vant/lib/index.css';
//3.引入注册组件
import ElementPlus from 'element-plus'
//4引入路由配置
import router from './router'
// 5.引入pinia状态管理工具
import pinia from './store';

//国际化标准
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'

// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}



app.use(ElementPlus,{
    locale: zhCn,
})
app.use(router)
app.use(pinia)
app.use(vant)
app.mount('#app')
