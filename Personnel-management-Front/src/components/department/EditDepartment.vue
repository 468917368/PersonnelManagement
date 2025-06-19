<script setup>
  import {useRouter,useRoute } from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
  import {onMounted} from 'vue'
  import {defineDepartment} from '../../store/departmentStore.js'
  

  //路由跳转
  let router = useRouter()
  let route = useRoute()
  let userStore = defineUser()

  const  id  = route.params.id  //获取路由参数  部门id

  let department = reactive({
    deptName:'',
    deptCode:'',
    parentId:'',
    leaderId:'',
    description:''
  })

  //根据id获取部门信息
  async function getDepartmentById(){
    let res = await axios.get(`/department/${id}`)
    department.deptName = res.data.data.deptName
    department.deptCode = res.data.data.deptCode
    department.parentId = res.data.data.parentId
    department.leaderId = res.data.data.leaderId
    department.description = res.data.data.description
  }

  //修改部门信息
  async function updateDepartment(){
    // 非空校验（建议同时做trim处理）
    if (!department.deptName?.trim()  || 
        !department.deptCode?.trim()  || 
        !department.description?.trim())  {
      alert('部门名称、编号和描述为必填项')
      return 
    }
    let {data} = await axios.put(`/department/${id}`,department)
    console.log(data.code)
    if(data.code == 200){
      alert('修改成功')
    }else{
      alert('修改失败')
    }
  }

  onMounted(()=>{
    getDepartmentById()
  })



</script>

<template>
  <div>
    <h1>请编辑部门信息</h1>
    <br>
    部门名称:<input type="text" v-model="department.deptName" >
    <br>
    部门编号:<input type="text" v-model="department.deptCode" >
    <br>
    上级部门id:<input type="text" v-model="department.parentId" >
    <br>
    部门领导id:<input type="text" v-model="department.leaderId" >
    <br>
    部门描述<input type="text" v-model="department.description" >
    <br>
    <button @click="updateDepartment()">提交修改</button>
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
}

/* 保存按钮 */
button[type="submit"] {
  background-color: #2ecc71;
  color: white;
}

button[type="submit"]:hover {
  background-color: #27ae60;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 取消按钮 */
button[type="button"] {
  background-color: #e74c3c;
  color: white;
}

button[type="button"]:hover {
  background-color: #c0392b;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
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
</style>