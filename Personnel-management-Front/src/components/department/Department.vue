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

  //pinia中存储所有部门信息
  let department = defineDepartment()

  

  //向后端发请求,获取所有部门信息
  async function getDepartmentList(){
    let {data} = await axios.get('/department/list')
    if(data.code === 200){
      department.departmentList = data.data
    }else{
      alert(data.message)
    }
  }

  //添加部门
  function addDepartment(){
    router.push('/addDepartment')
  }

  //编辑部门
  function editDepartment(id){
    router.push(`/editDepartment/${id}`) 
  }

  //根据部门id删除部门
  async function deleteDepartment(id){
    if (!confirm('确定要删除该部门吗？')) return 
    let { data } = await axios.delete(`/department/delete/${id}`) 
    if(data.code === 200){
      alert("删除成功")
      await getDepartmentList() // 重新获取部门列表
    }else{
      alert(data.message)
    }
  }

  //挂载完毕后,向后端发请求,返回所有部门信息
  onMounted(()=>{
    getDepartmentList()
  })
</script>

<template>
  <div>
    <h1>欢迎{{userStore.realName}}</h1> 
    <hr>
 
    <!-- 部门信息表格 -->
    <table>
      <thead>
        <tr>
          <th>部门ID</th>
          <th>部门名称</th>
          <th>部门编码</th>
          <th>上级部门</th>
          <th>负责人ID</th>
          <th>负责人</th>
          <th>部门描述</th>
          <th>创建时间</th>
          <th>更新时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in department.departmentList"  :key="item.id"> 
          <td>{{ item.id  }}</td>
          <td>{{ item.deptName  }}</td>
          <td>{{ item.deptCode  }}</td>
          <td>{{ item.parentId  }}</td>
          <td>{{ item.parentName  }}</td>
          <td>{{ item.leaderId  }}</td>
          <td>{{ item.leaderName  }}</td>
          <td>{{ item.description  }}</td>
          <td>{{ item.createTime  }}</td>
          <td>{{ item.updateTime  }}</td>
          <td><button @click="editDepartment(item.id)" >编辑部门</button></td>
          <td><button @click="deleteDepartment(item.id)" >删除部门</button></td>
          
        </tr>
      </tbody>
    </table>

    <br>
    <button @click="addDepartment()" >添加部门</button>
    <br>
    <button @click="router.push('/login')"> 退出登录</button>
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
  background-color: #3498db;
  margin: 0 5px;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

table button:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 编辑按钮 */
table button:first-child {
  background-color: #3498db;
}

table button:first-child:hover {
  background-color: #2980b9;
}

/* 删除按钮 */
table button:last-child {
  background-color: #e74c3c;
}

table button:last-child:hover {
  background-color: #c0392b;
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
