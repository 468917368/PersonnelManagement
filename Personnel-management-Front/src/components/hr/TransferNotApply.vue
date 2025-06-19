<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
  import{defineTransferList} from '../../store/hrStore.js'
  import { onMounted } from 'vue';

    //路由跳转
    let router = useRouter()
    let userStore = defineUser()

    //待审批的人事调动列表
    let list = defineTransferList()


    //获取已完成的人事调动列表 status = 0
    async function getNotTransferList(){
        let {data} = await axios.get('/transfer/notApply')
        if(data.code == 200){
            list.transferList = data.data
        }else{
            alert(data.msg)
        }
    }

    //审批
    async function approvalOrNot(id,status){
      if(!confirm('确定吗？'))
        return
      let {data} = await axios.post('/transfer/approvalOrNot',
        {
          id:id,
          status:status 
        })
        if(data.code == 200){
          alert(data.message)
          getNotTransferList() //更新待审批的人事调动列表
        }else{
          alert(data.message)
        }
    }


    //挂载
    onMounted(()=>{
      getNotTransferList()
    })

</script>

<template>

  <div>
        
        <h1>待审批人事调动列表</h1>
        <div>
            <table>
                <thead>
                    <tr>
                    <th>员工ID</th>
                    <th>员工姓名</th>
                    <th>原部门</th>
                    <th>新部门</th>
                    <th>原职位</th>
                    <th>新职位</th>
                    <th>调动类型</th>
                    <th>调动原因</th>
                    <th>调动日期</th>
                    <th>是否审批</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in list.transferList" :key="item.id">
                    <td>{{ item.employeeId  }}</td>
                    <td>{{ item.employeeName  || '-' }}</td>
                    <td>{{ item.beforeDepartmentName  || '-' }}</td>
                    <td>{{ item.afterDepartmentName  || '-' }}</td>
                    <td>{{ item.beforePositionName  }}</td>
                    <td>{{ item.afterPositionName  }}</td>
                    <td>{{ item.transferType  ? '调岗' : '未知类型' }}</td>
                    <td>{{ item.transferReason  }}</td>
                    <td>{{ item.transferDate  }}</td>
                    <td>
                      <button @click="approvalOrNot(item.id,1)">通过</button>
                    </td>
                    <td>
                      <button @click="approvalOrNot(item.id,2)">拒绝</button>
                    </td>
                    </tr>
                </tbody>
                </table>
        </div>
        <button @click="router.push('/hr')">返回</button>
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
  margin: 0 5px;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

/* 通过按钮 */
table button:first-child {
  background-color: #2ecc71;
}

table button:first-child:hover {
  background-color: #27ae60;
  transform: translateY(-2px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 拒绝按钮 */
table button:last-child {
  background-color: #e74c3c;
}

table button:last-child:hover {
  background-color: #c0392b;
  transform: translateY(-2px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 返回按钮样式 */
div > button {
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

div > button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

div > button:active {
  transform: translateY(0);
}
</style>