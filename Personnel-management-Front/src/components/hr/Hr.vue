<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
  import {defineFinance} from '../../store/financyStore.js'
  import { onMounted } from 'vue';

    //路由跳转
    let router = useRouter()
    let userStore = defineUser()

</script>

<template>
  <div>
        <h1>欢迎{{userStore.realName}}</h1>
        <h1>人事管理员页面</h1>
        <br>
        <button @click="router.push('/hr/transferList')">查看已处理调动记录列表</button>
        <br>
        <button @click="router.push('/hr/transferNotApply')">查看待审批的调动</button>
        <br>
        <button @click="router.push('/login')">退出登录</button>
  </div>
</template>

<style scoped>
/* 整体容器样式 */
div {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Arial', sans-serif;
  color: #333;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

/* 标题样式 */
h1 {
  color: #2c3e50;
  margin-bottom: 10px;
  font-weight: 500;
}

h1:first-child {
  font-size: 24px;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
  margin-bottom: 20px;
}

h1:nth-child(2) {
  font-size: 22px;
  color: #34495e;
  margin-bottom: 25px;
}

/* 按钮样式 */
button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 12px 20px;
  margin: 10px 0;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
  width: 250px;
  text-align: center;
  display: block;
}

button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

button:active {
  transform: translateY(0);
}

/* 退出登录按钮特殊样式 */
button:last-child {
  background-color: #e74c3c;
  margin-top: 30px;
}

button:last-child:hover {
  background-color: #c0392b;
}
</style>