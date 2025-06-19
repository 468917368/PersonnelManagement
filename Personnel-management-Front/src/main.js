import { createApp } from 'vue'
import App from './App.vue'

//导入router模块
import routers from './routers/router.js'

//导入element-plus相关内容
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 导pinia
import { createPinia } from 'pinia'
// 创建pinia对象
let pinia= createPinia()

let app = createApp(App)

//绑定路由对象
app.use(routers)
// app中使用pinia功能
app.use(pinia)
//app中使用element-plus
app.use(ElementPlus)

//挂载视图
app.mount('#app')
