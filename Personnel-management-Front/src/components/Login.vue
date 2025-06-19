<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../utils/request.js'  
  import {defineUser} from '../store/userStore.js'

  let userStore = defineUser() //pinia中的userStore对象

  let loginUser = reactive( //发请求时的对象数据
    {
      username:'',
      password:''
    }
  )
  //提示词
  let usernameMsg = ref('') 
  let passwordMsg = ref('') 

    // 添加成功提示信息
  let successMsg = ref('')
  let showSuccess = ref(false)

  //路由跳转
  let router = useRouter()


      function checkUsername(){
          //正则表达式
          let usernameReg=/^[a-zA-Z0-9]{4,20}$/
          if(!usernameReg.test(loginUser.username)){
            //ref  .value
            usernameMsg.value='用户名格式错误'
            // alert("账号格式错误")
            return false
          }
          //后续使用axios来与后端交互,不用原生的ajax
          usernameMsg.value=''
            return true
       }

       function checkUserPwd(){
         //正则表达式
         let userPwdReg=/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]{6,}$/
          if(!userPwdReg.test(loginUser.password)){
            passwordMsg.value='密码格式错误'
            return false
          }
          passwordMsg.value=''
            return true
       }

       async function login(){
          if(checkUsername() && checkUserPwd()){
            //发送axios请求
            let {data} = await axios.post('/login',loginUser)
            if(data.code == 200){
                // 显示成功消息
                successMsg.value = '登录成功！正在跳转页面...'
                showSuccess.value = true
                // 1秒后自动隐藏消息并跳转
                setTimeout(() => {
                    showSuccess.value = false
                    // 存储用户信息到store
                    userStore.id = data.data.id
                    userStore.username = data.data.username
                    userStore.realName = data.data.realName
                    userStore.roleCode = data.data.roleCode
                    // 跳转到首页或其他页面
                    if (data.data.roleCode == 'ROLE_ADMIN') { //系统管理员
                      router.push('/admin')
                    }else if (data.data.roleCode == 'ROLE_HR') { //人事管理员
                      router.push('/hr')
                    }else if (data.data.roleCode == 'ROLE_FINANCE') { //薪资管理员
                      router.push('/finance')
                    }else if (data.data.roleCode == 'ROLE_DEPT_HEAD') { //部门管理员
                      router.push('/department')
                    }else if (data.data.roleCode == 'ROLE_EMPLOYEE') { //普通员工
                      router.push('/employee')
                    }
                  }, 1000)
            }else {
                  // 显示错误信息
                  passwordMsg.value = data.message || '登录失败，请检查用户名和密码'
            }
      }
      }

        // 添加重置表单函数
      function resetForm() {
        // 重置登录信息
        loginUser.username = ''
        loginUser.password = ''
        
        // 清空提示信息
        usernameMsg.value = ''
        passwordMsg.value = ''
        showSuccess.value = false
      }


</script>

<template>
  <div class="login-container">
    <h1 class="login-title">人力资源管理系统</h1>
    <div class="login-form">
      <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="loginUser.username" @blur="checkUsername" placeholder="请输入用户名">
        <div class="error-message">{{ usernameMsg }}</div>
      </div>
      
      <div class="form-group">
        <label for="password">密码</label>
        <input type="password" id="password" v-model="loginUser.password" @blur="checkUserPwd" placeholder="请输入密码">
        <div class="error-message">{{ passwordMsg }}</div>
      </div>
      
      <button class="login-button" @click="login">登录</button>
      <button class="reset-button" @click="resetForm">重置</button>
      
      <div v-if="showSuccess" class="success-message">
        {{ successMsg }}
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 登录页面容器 */
.login-container {
  max-width: 400px;
  margin: 80px auto;
  padding: 30px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);
  text-align: center;
}

/* 标题样式 */
.login-title {
  color: #2c3e50;
  font-size: 28px;
  margin-bottom: 30px;
  font-weight: 500;
  position: relative;
}

.login-title::after {
  content: '';
  position: absolute;
  bottom: -10px;
  left: 50%;
  transform: translateX(-50%);
  width: 60px;
  height: 3px;
  background-color: #3498db;
}

/* 表单样式 */
.login-form {
  margin-top: 20px;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #555;
  font-weight: 500;
  font-size: 14px;
}

.form-group input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.form-group input:focus {
  border-color: #3498db;
  outline: none;
  box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
}

/* 错误消息样式 */
.error-message {
  color: #e74c3c;
  font-size: 12px;
  margin-top: 5px;
  text-align: left;
  height: 15px;
}

/* 按钮样式 */
.login-button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 12px 0;
  margin-top: 10px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  width: 100%;
  transition: background-color 0.3s;
}

.login-button:hover {
  background-color: #2980b9;
}

.reset-button {
  background-color: #95a5a6;
  color: white;
  border: none;
  padding: 12px 0;
  margin-top: 10px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  width: 100%;
  transition: background-color 0.3s;
}

.reset-button:hover {
  background-color: #7f8c8d;
}

/* 成功消息样式 */
.success-message {
  background-color: #2ecc71;
  color: white;
  padding: 10px;
  border-radius: 4px;
  margin-top: 20px;
  text-align: center;
  font-weight: 500;
  animation: fadeIn 0.5s;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(-10px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>