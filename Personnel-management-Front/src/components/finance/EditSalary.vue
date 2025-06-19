<script setup>
  import {useRouter,useRoute} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
  import {defineFinance} from '../../store/financyStore.js'
  import { onMounted } from 'vue'


    //获取路由
    let route  = useRoute()
    const  id  = route.params.id  //获取路由参数  薪资id

    //存储薪资信息
    let salaryInfo = ref({})


  
    //路由跳转
    let router = useRouter()

  //根据薪资id序号获取薪资信息
  async function getSalaryById(id){
    let {data} = await axios.get(`/finance/salary/${id}`)
    if(data.code === 200){
        salaryInfo.value = data.data
    }else{
      alert('获取薪资信息失败')
    }
  }

  //修改薪资信息
  async function updateSalary(){
    let {data} = await axios.put(`/finance/salary`,salaryInfo.value)
    if(data.code === 200){
        alert('修改成功')
    }else{
      alert('修改失败')
    }
  }

  //根据薪资id获取薪资信息
  onMounted(()=>{
    getSalaryById(id)
  })



</script>

<template>
  <div>
    <h1>请编辑薪资信息</h1>
    <br>
        <div>
            序号:{{ salaryInfo.id  }} <br>
            员工ID:{{ salaryInfo.employeeId  }} <br>
            基本工资:<input type="text" v-model="salaryInfo.basicSalary"> <br>
            岗位工资:<input type="text" v-model="salaryInfo.positionSalary"> <br>
            绩效工资:<input type="text" v-model="salaryInfo.performanceSalary"> <br>
            奖金:<input type="text" v-model="salaryInfo.bonus"> <br>
            社保扣除:<input type="text" v-model="salaryInfo.insurance"> <br>
            个税扣除:<input type="text" v-model="salaryInfo.tax"> <br>
            实发工资:{{salaryInfo.actualSalary}}<br>
            发放月份:{{ salaryInfo.salaryMonth  }}<br>
            发放状态:{{ salaryInfo.status  === true ? '已发放' : '未发放' }} <br>
        </div>

    <button @click="updateSalary()">提交修改</button>
    <br>
    <button @click="router.push('/finance')">返回</button>

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
input[type="number"] {
  flex: 1;
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s, box-shadow 0.3s;
  max-width: 300px;
}

input[type="text"]:focus,
input[type="number"]:focus {
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