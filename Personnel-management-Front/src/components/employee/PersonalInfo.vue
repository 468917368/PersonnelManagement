<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'

  //路由跳转

  let router = useRouter()
  let userStore = defineUser()
  
  // 员工信息数据  没有必要存储到pinia中 只在此页面展示
  const employeeInfo = ref({})
  
  // 获取员工个人信息
  const getEmployeeInfo = async () => {
    try {
      const response = await axios.get(`/employee/info/${userStore.realName}`)
      if (response.data.code === 200) {
        employeeInfo.value = response.data.data
      } else {
        // 处理错误情况
        console.error('获取员工信息失败:', response.data.message)
      }
    } catch (error) {
      console.error('获取员工信息异常:', error)
    }
  }

  //修改个人信息
  async function modifyInfo(){
      let {data} = await axios.put(`/employee/update`,employeeInfo.value)
      if(data.code === 200){
        alert('修改成功')
        //更新修改的用户数据
        employeeInfo.phone = data.data.phone
        employeeInfo.email = data.data.email
        employeeInfo.address = data.data.address
      }else{
        alert('修改失败')
      }
  }
  
  // 页面加载时获取数据
  getEmployeeInfo()
</script>

<template>
<div>
    <h1>个人信息</h1> <br>
    
    <div class="info-table-container" v-if="employeeInfo.id">
      <table class="info-table">
        <tbody>
          <tr>
            <td class="label">员工编号</td>
            <td class="value">{{ employeeInfo.employeeNo }}</td>
            <td class="label">姓名</td>
            <td class="value">{{ employeeInfo.name }}</td>
          </tr>
          <tr>
            <td class="label">性别</td>
            <td class="value">{{ employeeInfo.gender === 1 ? '男' : '女' }}</td>
            <td class="label">出生日期</td>
            <td class="value">{{ employeeInfo.birthDate }}</td>
          </tr>
          <tr>
            <td class="label">身份证号</td>
            <td class="value">{{ employeeInfo.idCard }}</td>
            <td class="label">手机号</td>
            <!-- 可以修改,最后提交修改 -->
            <td class="value">
              <input type="text" v-model="employeeInfo.phone">
            </td>
          </tr>
          <tr>
            <td class="label">邮箱</td>
            <td class="value">
              <input type="text" v-model="employeeInfo.email">
            </td>
            <td class="label">住址</td>
            <td class="value">
              <input type="text" v-model="employeeInfo.address">
            </td>
          </tr>
          <tr>
            <td class="label">学历</td>
            <td class="value">{{ employeeInfo.education }}</td>

            <!-- 职位和部门要根据id进行转换 -->

            <td class="label">职位</td>
            <td class="value">{{ employeeInfo.positionName }}</td>
          </tr>
          <tr>
            <td class="label">部门</td>
            <td class="value">{{ employeeInfo.departmentName }}</td>
            <td class="label">入职日期</td>
            <td class="value">{{ employeeInfo.entryDate }}</td>
          </tr>
          <tr>
            <td class="label">状态</td>
            <td class="value">{{ employeeInfo.status === 1 ? '在职' : '离职' }}</td>
          </tr>
          <tr>
            <td class="label">创建时间</td>
            <td class="value">{{ employeeInfo.createTime }}</td>
          </tr>
          <tr>
            <td class="label">更新时间</td>
            <td class="value">{{ employeeInfo.updateTime }}</td>
            <td></td>
            <td></td>
          </tr>
        </tbody>
      </table>
    </div>
    
    <div v-else class="loading">
      正在加载个人信息...
    </div>

    <br>
        <button @click="router.push('/employee')">返回</button>
    <br>
        <button @click="modifyInfo()">提交修改</button>
    
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
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  margin-bottom: 20px;
}

/* 表单行样式 */
.form-row {
  margin-bottom: 15px;
  display: flex;
  align-items: center;
}

/* 标签样式 */
label {
  width: 120px;
  text-align: right;
  margin-right: 15px;
  font-weight: 500;
  color: #34495e;
}

/* 输入框样式 */
input[type="text"],
input[type="password"],
input[type="email"],
input[type="date"],
select {
  flex: 1;
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s, box-shadow 0.3s;
  max-width: 300px;
}

input[type="text"]:focus,
input[type="password"]:focus,
input[type="email"]:focus,
input[type="date"]:focus,
select:focus {
  border-color: #3498db;
  outline: none;
  box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
}

/* 错误提示样式 */
.error {
  color: #e74c3c;
  font-size: 12px;
  margin-left: 135px;
  margin-top: 5px;
}

/* 信息展示样式 */
.info-item {
  margin-bottom: 15px;
  display: flex;
}

.info-label {
  width: 120px;
  text-align: right;
  margin-right: 15px;
  font-weight: 500;
  color: #34495e;
}

.info-value {
  flex: 1;
  color: #333;
}

/* 按钮样式 */
button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 10px 0;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
  width: 200px;
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

/* 提交按钮特殊样式 */
button[type="submit"] {
  background-color: #2ecc71;
  margin-left: 135px;
}

button[type="submit"]:hover {
  background-color: #27ae60;
}

/* 返回按钮特殊样式 */
div > button:last-of-type {
  background-color: #3498db;
  margin-left: 0;
}

div > button:last-of-type:hover {
  background-color: #2980b9;
}
</style>
