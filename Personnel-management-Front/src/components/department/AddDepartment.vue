<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
  import {onMounted} from 'vue'
  import {defineDepartment} from '../../store/departmentStore.js'
  

  //路由跳转
  let router = useRouter()
  let userStore = defineUser()

  let department = reactive({
    deptName:'',
    deptCode:'',
    parentId:'',
    leaderId:'',
    description:''
  })

  //添加部门信息
  async function addDepartment(){
    // 非空校验（建议同时做trim处理）
    if (!department.deptName?.trim()  || 
        !department.deptCode?.trim()  || 
        !department.description?.trim())  {
      alert('部门名称、编号和描述为必填项')
      return 
    }
    //非空发送添加部门请求
    let {data} = await axios.post('/department/add',department)
    if(data.code == 200){
      alert('添加成功')
      department.value = {} //清空数据
    }else{
      alert('添加失败,请检查输入是否正确')
      department.value = {} //清空数据
    }
  }


</script>

<template>
  <div>
    <h1>请输入要添加的部门信息</h1>
    <br>
    部门名称:<input type="text" v-model="department.deptName" placeholder="请输入部门名称">
    <br>
    部门编号:<input type="text" v-model="department.deptCode" placeholder="请输入部门编号">
    <br>
    上级部门id:<input type="text" v-model="department.parentId" placeholder="请输入上级部门id">
    <br>
    部门领导id:<input type="text" v-model="department.leaderId" placeholder="请输入部门领导id">
    <br>
    部门描述:<input type="text" v-model="department.description" placeholder="请输入部门描述">
    <br>
    <button @click="addDepartment">提交</button>
    <br>
    <button @click="router.push('/department')">返回</button>

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
  margin-bottom: 20px;
  font-weight: 500;
  font-size: 24px;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

/* 表单样式 */
form {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 1px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

/* 表单项样式 */
.form-item {
  margin-bottom: 15px;
}

.form-item label {
  display: block;
  margin-bottom: 5px;
  font-weight: 500;
  color: #2c3e50;
}

.form-item input,
.form-item textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  transition: border 0.3s ease;
}

.form-item input:focus,
.form-item textarea:focus {
  border-color: #3498db;
  outline: none;
}

/* 错误信息样式 */
.error-message {
  color: #e74c3c;
  font-size: 12px;
  margin-top: 5px;
}

/* 按钮容器 */
.button-container {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}

/* 按钮样式 */
button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
  background-color: #3498db;
  color: white;
  margin: 10px 0;
}

button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 添加按钮 */
button[type="submit"],
button:first-of-type {
  background-color: #2ecc71;
  color: white;
}

button[type="submit"]:hover,
button:first-of-type:hover {
  background-color: #27ae60;
}

/* 取消按钮 */
button[type="button"],
button:last-of-type {
  background-color: #3498db;
  color: white;
}

button[type="button"]:hover,
button:last-of-type:hover {
  background-color: #2980b9;
}

/* 返回按钮 */
.back-button {
  background-color: #3498db;
  color: white;
  margin-top: 20px;
  width: 200px;
  text-align: center;
  display: block;
}

.back-button:hover {
  background-color: #2980b9;
}

/* 输入框样式 */
input {
  width: 100%;
  padding: 10px;
  margin: 8px 0;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  transition: border 0.3s ease;
}

input:focus {
  border-color: #3498db;
  outline: none;
  box-shadow: 0 0 5px rgba(52, 152, 219, 0.3);
}
</style>