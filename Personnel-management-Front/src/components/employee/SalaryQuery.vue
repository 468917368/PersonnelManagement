<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'

  //路由跳转
  let router = useRouter()
  let userStore = defineUser()
  
  // 薪资信息数据
  let salaryInfo = ref({})

    // 获取当前日期
    const currentDate = new Date()
    // 初始值为当前年月
    const salaryMonth = ref(String(currentDate.getMonth() + 1).padStart(2, '0'))  
    const salaryYear = ref(String(currentDate.getFullYear()))

  
  // 获取员工薪资信息
  async function getSalaryInfo() {
    try {
      let {data} = await axios.get('/employee/salaryInfo', {
        params: {
          //根据id,年月查询
          id: userStore.id,
          salaryYear: salaryYear.value,
          salaryMonth: salaryMonth.value
        }
      })
      if (data.code === 200) {
        salaryInfo.value = data.data
      } else {
        alert(data.message)
        salaryInfo.value  = {}; // 清空数据 
      }
    } catch (error) {
      console.log(error)
    }
  }
  
  // 页面加载时获取薪资信息
  getSalaryInfo()
</script>

<template>
  <div>
    <h1>{{ userStore.realName }}的薪资信息</h1>
    <br>
    请输入您要查询的年月:<input type="text" v-model="salaryYear">年<input type="text" v-model="salaryMonth">月
    <br>
    <button @click="getSalaryInfo()">查询</button>
    <br>
    
    <div>
      <table border="1">
        <thead>
          <tr>
            <th>薪资项目</th>
            <th>金额（元）</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>基本工资</td>
            <td>{{ salaryInfo.basicSalary }}</td>
          </tr>
          <tr>
            <td>职位工资</td>
            <td>{{ salaryInfo.positionSalary }}</td>
          </tr>
          <tr>
            <td>绩效工资</td>
            <td>{{ salaryInfo.performanceSalary }}</td>
          </tr>
          <tr>
            <td>奖金</td>
            <td>{{ salaryInfo.bonus }}</td>
          </tr>
          <tr>
            <td>保险</td>
            <td>{{ salaryInfo.insurance }}</td>
          </tr>
          <tr>
            <td>个税</td>
            <td>{{ salaryInfo.tax }}</td>
          </tr>
          <tr>
            <td><strong>实发工资</strong></td>
            <td><strong>{{ salaryInfo.actualSalary }}</strong></td>
          </tr>
        </tbody>
      </table>
      <p>工资月份：{{ salaryInfo.salaryMonth }}</p>
      <p>发放状态：{{ salaryInfo.status ? '已发放' : '未发放' }}</p>
    </div>
    

    
    <button @click="router.push('/employee')">返回</button>
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

/* 查询表单样式 */
.query-form {
  display: flex;
  gap: 15px;
  margin-bottom: 20px;
  align-items: center;
  flex-wrap: wrap;
}

.query-form input,
.query-form select {
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  flex: 1;
  min-width: 120px;
  max-width: 200px;
}

.query-form button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 8px 15px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
}

.query-form button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 表格样式 */
table {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
  background-color: white;
  box-shadow: 0 1px 8px rgba(0, 0, 0, 0.1);
  border-radius: 4px;
  overflow: hidden;
}

thead {
  background-color: #34495e;
  color: white;
}

th {
  padding: 12px 15px;
  text-align: left;
  font-weight: 500;
}

td {
  padding: 10px 15px;
  border-bottom: 1px solid #eee;
}

tr:last-child td {
  border-bottom: none;
}

tr:hover {
  background-color: #f5f7fa;
}

/* 薪资金额样式 */
.salary-amount {
  font-weight: 600;
  color: #27ae60;
}

/* 返回按钮样式 */
.back-button {
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

.back-button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.back-button:active {
  transform: translateY(0);
}

/* 无数据提示样式 */
.no-data {
  text-align: center;
  padding: 20px;
  color: #7f8c8d;
  font-style: italic;
}

/* 为普通按钮添加样式 */
button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 8px 15px;
  margin: 10px 0;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
}

button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 输入框样式 */
input {
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin: 0 5px;
  font-size: 14px;
}
</style>