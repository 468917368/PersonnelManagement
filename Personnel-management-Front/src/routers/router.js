// 导入路由创建的相关方法
import {createRouter,createWebHashHistory} from 'vue-router'


import {defineUser} from '../store/userStore.js'


// 导入vue组件
import Login from '../components/Login.vue'
import Employee from '../components/employee/Employee.vue'
import PersonalInfo from '../components/employee/PersonalInfo.vue';
import SalaryQuery from '../components/employee/SalaryQuery.vue';
import Department from '../components/department/Department.vue';
import AddDepartment from '../components/department/AddDepartment.vue';
import EditDepartment from '../components/department/EditDepartment.vue';
import Finance from '../components/finance/Finance.vue';
import EditSalary from '../components/finance/editSalary.vue';
import Hr from '../components/hr/Hr.vue';
import TransferList from '../components/hr/TransferList.vue';
import TransferNotApply from '../components/hr/TransferNotApply.vue';
import AddUser from '../components/admin/AddUser.vue';
import Admin from '../components/admin/Admin.vue';
import UserList from '../components/admin/UserList.vue';

// 创建路由对象,声明路由规则
const router = createRouter({
    history: createWebHashHistory(),
    routes:[
        {
            path:'/login',
            component: Login
        },
        {
            path:'/',
            redirect: '/Login'
        },
        {
            path:'/employee',
            component: Employee
        },
        {
            path:'/personalInfo',
            component: PersonalInfo
        },
        {
            path:'/salaryQuery',
            component: SalaryQuery
        },
        {
            path:'/department',
            component: Department
        },
        {
            path:'/addDepartment',
            component: AddDepartment
        },
        {
            path:'/editDepartment/:id',
            component: EditDepartment
        } ,
        {
            path:'/finance',
            component: Finance
        },        
        {
            path:'/editSalary/:id',
            component: EditSalary
        } ,
        {
            path:'/hr',
            component: Hr
        } ,
        {
            path:'/hr/transferList',
            component: TransferList
        } ,
        {
            path:'/hr/transferNotApply',
            component: TransferNotApply
        } ,
        {
            path:'/admin',
            component: Admin
        }  ,
        {
            path:'/admin/addUser',
            component: AddUser
        }  ,
        {
            path:'/admin/userList',
            component: UserList
        } 
    ]
})


// 全局前置守卫
router.beforeEach(
    
(to, from, next) => {
  // 如果访问的是登录页面或根路径，直接放行
  if (to.path === '/login') {
    next()
    return
  }

  if(to.path != '/login'){
    const userStore = defineUser()
      // 检查用户是否已登录
        if (userStore.realName == '') {
            // 弹窗提示
            alert('您尚未登录，请先登录')
            // 重定向到登录页面
            next('/login')
            return
        }
  }
  // 已登录，放行
  next()
})

// 对外暴露路由对象
export default router;