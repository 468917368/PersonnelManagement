<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
  import {defineFinance} from '../../store/financyStore.js'
  import { onMounted } from 'vue';

  //存储薪资信息
    let  finance = defineFinance()
  

    //路由跳转
    let router = useRouter()
    let userStore = defineUser()

    let employeeId = ref('')

    // 获取当前日期
    const currentDate = new Date()
    // 初始值为当前年月
    const salaryMonth = ref(String(currentDate.getMonth() + 1).padStart(2, '0'))  
    const salaryYear = ref(String(currentDate.getFullYear()))

    //根据选择的条件进行薪资查询
    async function querySalary() {
        try {
        let {data} = await axios.get('/finance/salarys', {
            params: {
            //根据id,年月查询
            employeeId: employeeId.value,
            salaryYear: salaryYear.value,
            salaryMonth: salaryMonth.value
            }
        })
        if (data.code === 200) {
            finance.salaryList = data.data
        } else {
            alert(data.message)
            finance.salaryList  = {}; // 清空数据 
        }
        } catch (error) {
        console.log(error)
    }
    }

    // 修改薪资信息
    function editSalary(id) {
        router.push(`/editSalary/${id}`)
    }


  onMounted(()=>{
    querySalary()
  })

</script>

<template>

  <div>
        <h1>欢迎{{userStore.realName}}</h1>
        <h1>工资管理页</h1>
        请选择您要查询的员工编号:<input type="text" v-model="employeeId">(可选)
        <br>
        请输入您要查询的年月:<input type="text" v-model="salaryYear">年(可选)<input type="text" v-model="salaryMonth">月(可选)
        <br>
        <button @click="querySalary()">查询</button>

        <br>
        <div>
    <table>
        <thead>
            <tr>
                <th>序号</th>
                <th>员工ID</th>
                <th>基本工资</th>
                <th>岗位工资</th>
                <th>绩效工资</th>
                <th>奖金</th>
                <th>社保扣除</th>
                <th>个税扣除</th>
                <th>实发工资</th>
                <th>发放月份</th>
                <th>发放状态</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="item in finance.salaryList"  :key="item.id"> 
                <td>{{ item.id  }}</td>
                <td>{{ item.employeeId  }}</td>
                <td>{{ item.basicSalary  }}</td>
                <td>{{ item.positionSalary  }}</td>
                <td>{{ item.performanceSalary  }}</td>
                <td>{{ item.bonus  }}</td>
                <td>{{ item.insurance  }}</td>
                <td>{{ item.tax  }}</td>
                <td>{{ item.actualSalary  }}</td>
                <td>{{ item.salaryMonth  }}</td>
                <td>{{ item.status  === true ? '已发放' : '未发放' }}</td>
                <td>
                    <button @click="editSalary(item.id)">修改</button>
                </td>
            </tr>
        </tbody>
    </table>
</div>


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

/* 表单元素样式 */
input[type="text"] {
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin: 0 8px;
  font-size: 14px;
  transition: border-color 0.3s;
}

input[type="text"]:focus {
  border-color: #3498db;
  outline: none;
  box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
}

/* 按钮样式 */
button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 8px 16px;
  margin: 10px 0;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
}

button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

button:active {
  transform: translateY(0);
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

/* 表格中的按钮样式 */
table button {
  padding: 6px 12px;
  font-size: 12px;
  background-color: #3498db;
  margin: 0;
}

table button:hover {
  background-color: #2980b9;
}

/* 退出登录按钮特殊样式 */
div > button:last-child {
  background-color: #e74c3c;
  margin-top: 30px;
  padding: 10px 20px;
  width: 200px;
}

div > button:last-child:hover {
  background-color: #c0392b;
}
</style>