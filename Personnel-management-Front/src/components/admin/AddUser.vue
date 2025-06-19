<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineFinance} from '../../store/financyStore.js'
  import { onMounted } from 'vue';
  import {defineUser} from '../../store/adminStore.js'

    //路由跳转
    let router = useRouter()

      //提示词
    let usernameMsg = ref('') 
    let passwordMsg = ref('') 

    let user = defineUser()

    function checkUsername(){
          //正则表达式
          let usernameReg=/^[a-zA-Z0-9]{4,20}$/
          if(!usernameReg.test(user.userInfo.username)){
            usernameMsg.value='用户名格式错误'
            return false
          }
          usernameMsg.value=''
            return true
       }

       function checkUserPwd(){
         //正则表达式
         let userPwdReg=/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]{6,}$/
          if(!userPwdReg.test(user.userInfo.password)){
            passwordMsg.value='密码格式错误'
            return false
          }
          passwordMsg.value=''
            return true
       }

       async function addUser(){
        if(!checkUsername() || !checkUserPwd()){
          return
        }

        //所有数据非空校验
      // 统一非空校验函数 
      const isEmpty = (value) => 
        value === "" || value === null || value === undefined;
    
      // 需要校验的字段列表 
        const validationRules = [
          { value: user.userInfo.email,   message: "邮箱不能为空" },
          { value: user.userInfo.phone,   message: "手机号不能为空" },
          { value: user.userInfo.realName,   message: "真实姓名不能为空" },
          { value: user.employeeInfo.employeeNo,   message: "员工编号不能为空" },
          { value: user.employeeInfo.gender,   message: "性别不能为空" },
          { value: user.employeeInfo.birthDate,   message: "生日不能为空" },
          { value: user.employeeInfo.idCard,   message: "身份证号不能为空" },
          { value: user.employeeInfo.address,   message: "住址不能为空" },
          { value: user.employeeInfo.education,   message: "学历不能为空" },
          { value: user.employeeInfo.positionId,   message: "职位不能为空" },
          { value: user.employeeInfo.departmentId,   message: "部门不能为空" },
          { value: user.roleId,   message: "角色身份不能为空" }
        ];
    
      // 执行校验 
      const invalidField = validationRules.find(item  => isEmpty(item.value)); 
      if (invalidField) {
        alert(invalidField.message); 
        return;
      }

        //发送请求
         let {data} = await axios.post('/admin/add',
         {
          userInfo : user.userInfo,
          employeeInfo : user.employeeInfo,
           roleId : user.roleId
         }
         )
         if(data.code == 200){
           alert(data.message)
           user.userInfo = {} //清空数据
           user.employeeInfo = {} //清空数据
           user.roleId = '' //清空数据
         }else{
           alert(data.message)
           user.userInfo = {} //清空数据
           user.employeeInfo = {} //清空数据
           user.roleId = '' //清空数据
         }
       }

</script>

<template>

  <div>
        
        <h1>请输入要添加的员工信息</h1>
        <br>
        昵称:<input type="text" v-model="user.userInfo.username" placeholder="请输入用户昵称" @blur="checkUsername()"> 
        <span id="usernameMsg" v-text="usernameMsg"></span> <br>

        密码:<input type="text" v-model="user.userInfo.password" placeholder="请输入用户密码" @blur="checkUserPwd()">
         <span id="passwordMsg" v-text="passwordMsg"></span> <br>

        邮箱:<input type="text" v-model="user.userInfo.email" placeholder="请输入用户邮箱">  <br>
        手机号:<input type="text" v-model="user.userInfo.phone" placeholder="请输入用户手机号"><br>
        真实姓名:<input type="text" v-model="user.userInfo.realName" placeholder="请输入用户真实姓名">  <br>

        编号:<input type="text" v-model="user.employeeInfo.employeeNo" placeholder="请输入用户编号">  <br>



        性别:<input type="radio" v-model="user.employeeInfo.gender"  :value="1"> 男 
        <input type="radio" v-model="user.employeeInfo.gender" :value="0"> 女 <br>

        生日:<input type="text" v-model="user.employeeInfo.birthDate" placeholder="请输入用户生日(如：1995-08-12)"><br>
        身份证号:<input type="text" v-model="user.employeeInfo.idCard" placeholder="请输入用户身份证号"><br>
        住址:<input type="text" v-model="user.employeeInfo.address" placeholder="请输入用户住址"><br>

        学历:<select v-model="user.employeeInfo.education"> 
          <option value="专科">专科</option>
          <option value="本科">本科</option>
          <option value="硕士">硕士</option>
          <option value="博士">博士</option>
          <option value="博士后">博士后</option>
        </select> <br>

        职位:<select v-model="user.employeeInfo.positionId"> 
          <option value="1">总经理</option>
          <option value="2">人事经理</option>
          <option value="3">财务专员</option>
          <option value="4">技术总监</option>
          <option value="5">市场经理</option>
          <option value="6">高级工程师</option>
          <option value="7">初级工程师</option>
          <option value="8">人事专员</option>
        </select>
        <br>


        部门:<select v-model="user.employeeInfo.departmentId"> 
          <option value="1">总公司</option>
          <option value="2">人事部</option>
          <option value="3">财务部</option>
          <option value="4">技术部</option>
          <option value="5">市场部</option>
          <option value="6">技术一部</option>
          <option value="7">技术二部</option>
          <option value="9">技术三部</option>
          <option value="10">技术四部</option>
        </select>
        <br>

        身份:<select v-model="user.roleId"> 
          <option value="1">系统管理员</option>
          <option value="2">人事经理</option>
          <option value="3">财务专员</option>
          <option value="4">部门主管</option>
          <option value="5">普通员工</option>
        </select>
        <br>
        
        <br>
        <button @click="addUser()">提交</button>
        
        <br>
        <button @click="router.push('/admin')">返回</button>
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

/* 单选按钮组样式 */
.radio-group {
  display: flex;
  gap: 15px;
}

.radio-item {
  display: flex;
  align-items: center;
}

.radio-item input[type="radio"] {
  margin-right: 5px;
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

/* 重置按钮特殊样式 */
button[type="reset"] {
  background-color: #e74c3c;
  margin-left: 135px;
}

button[type="reset"]:hover {
  background-color: #c0392b;
}

/* 返回按钮特殊样式 */
div > button:last-of-type {
  background-color: #3498db;
  margin-left: 135px;
}

div > button:last-of-type:hover {
  background-color: #2980b9;
}
</style>