<script setup>
  import {useRouter} from 'vue-router'
  import {ref,reactive} from 'vue'
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
  import { onMounted } from 'vue';
  import {defineUserList} from '../../store/adminStore.js'

    //路由跳转
    let router = useRouter()
    let userStore = defineUser()

    let list = defineUserList()

    //获取员工列表
    async function getUserList(){
      let {data} = await axios.get('/admin/userList')
      if(data.code == 200){
        list.userList = data.data
      }else{
        alert(data.message)
      }
    }

    //根据id删除员工
    async function deleteUserById(id){
      if(! confirm('确定删除吗？')){
        return
      }
      let {data} = await axios.delete(`/admin/deleteUser/${id}`)
      if(data.code == 200){
        alert(data.message)
        getUserList() //刷新列表
      }else{
        alert(data.message)
      }
    }


    onMounted(()=>{
      getUserList()
    })


</script>

<template>

  <div>
        
        <h1>当前所有员工信息</h1>

        <div>
          <table>
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>昵称</th>
                    <th>姓名</th>
                    <th>邮箱</th>
                    <th>电话</th>
                    <th>状态</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in list.userList" :key="item.id">
                    <td>{{ item.id  }}</td>
                    <td>{{ item.username  || '-' }}</td>
                    <td>{{ item.realName  || '-' }}</td>
                    <td>{{ item.email  || '-' }}</td>
                    <td>{{ item.phone  }}</td>
                    <td>{{ item.status == 1 ? '正常' : '禁用'}}</td>
                    <td>{{ item.createTime }}</td>
                    <td>{{ item.updateTime }}</td>
                    <td>
                      <button @click="deleteUserById(item.id)">删除</button>
                    </td>
                    </tr>
                </tbody>
                </table>
        </div>        
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
  margin-bottom: 10px;
  font-weight: 500;
  font-size: 24px;
}

/* 分隔线样式 */
hr {
  border: none;
  height: 1px;
  background-color: #e0e0e0;
  margin: 15px 0 25px 0;
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
  background-color: #e74c3c;
  margin: 0;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

table button:hover {
  background-color: #c0392b;
  transform: translateY(-2px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 页面按钮样式 */
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

/* 退出登录按钮特殊样式 */
div > button:last-child {
  background-color: #e74c3c;
  margin-top: 30px;
}

div > button:last-child:hover {
  background-color: #c0392b;
}
</style>