# 01登录错误问题分析与解决方案

CORS 跨域问题

```
Access to XMLHttpRequest at 'http://localhost:8080/pmsys/login' from origin 'http://localhost:5173' has been blocked by CORS policy
```

浏览器的同源策略阻止了这种跨域请求。

解决方案：

在后端服务器添加 CORS 支持。添加以下配置：

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

# 02跳转到 Employee.vue 组件时遇到了错误

```
[Vue warn]: Property "userStore" was accessed during render but is not defined on instance.
Uncaught (in promise) TypeError: Cannot read properties of undefined (reading 'username')
```

没有正确导入和初始化

解决: 由于包的结构发生了变化,向上跳2级,以及defineUser忘记导入,但是后面的代码用到了

```
  import axios from '../../utils/request.js'  
  import {defineUser} from '../../store/userStore.js'
```



#  03Spring 无法找到 HrEmployeeMapper 的 bean 来注入到EmployeeServiceImpl 中

```
Field hrEmployeeMapper in com.hnust.personnelmanagement.service.impl.EmployeeServiceImpl required a bean of type 'com.hnust.personnelmanagement.mapper.UserMapper.HrEmployeeMapper' that could not be found.
```

对于 MyBatis 的 Mapper 接口，通常需要额外配置才能让 Spring 识别并创建它的实现类。

不能用@Repository注解

解决:

```
@Mapper
public interface HrEmployeeMapper{...}
```



# 04请求参数错误

1.后端接收到的参数,不是username

```
Parameters: ${userStore.username}(String)    
```

在JavaScript中，当你想要在字符串中插入变量时，需要使用模板字符串（template literals），这需要使用反引号(`)而不是单引号或双引号。

解决:

```
      const response = await axios.get(`/employee/info/${userStore.username}`)
```

2.后端需要的是中文name,但是前端给的是英文name

```
2025-05-28T09:24:26.080+08:00 DEBUG 24304 --- [nio-8080-exec-2] c.h.p.m.U.H.selectByUsername             : ==>  Preparing: 
SELECT e.*, p.position_name AS positionName, d.dept_name AS departmentName FROM hr_employee e LEFT JOIN hr_position p ON e.position_id = p.id LEFT JOIN hr_department d ON e.department_id = d.id WHERE e.name = ?

2025-05-28T09:24:26.082+08:00 DEBUG 24304 --- [nio-8080-exec-2] c.h.p.m.U.H.selectByUsername             : ==> Parameters: zhangsan(String)   错误❌
```

应该传realName





# 05复杂对象不要通过 URL 路径传递

```
PersonalInfo.vue:30   PUT http://localhost:8080/pmsys/employee/update 404 (Not Found)
```

修改后端的代码

```
    @PutMapping("/employee/update")
    public Result modifyEmployeeInfo(@RequestBody HrEmployee employeeInfo) {
        ...
    }
```



# 06MySQL默认不允许在单个PreparedStatement中执行多个语句

```
### Error updating database. Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'UPDATE hr_employee
SET
phone = '13800138005',
email = 'zhangsan@comp' at line 9
### The error may exist in file [E:\java-base-1\Personnel-management\target\classes\mapper\UserMapper\HrEmployeeMapper.xml]
### The error may involve defaultParameterMap
### The error occurred while setting parameters
### SQL: UPDATE sys_user SET phone = ?, email = ?, update_time = NOW() WHERE real_name = ?; UPDATE hr_employee SET phone = ?, email = ?, address = ?, update_time = NOW() WHERE name = ?;
### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'UPDATE hr_employee
SET
phone = '13800138005',
email = 'zhangsan@comp' at line 9
; bad SQL grammar []] with root cause
```

解决:

在数据库连接URL中添加`allowMultiQueries=true`参数

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/personnel_management?allowMultiQueries=true
```



# 07后端请求参数解析错误400

```
2025-05-28T14:47:14.751+08:00  WARN 14404 --- [nio-8080-exec-4] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: Required request body is missing: public com.hnust.personnelmanagement.bean.vo.Result com.hnust.personnelmanagement.controller.EmployeeController.getSalaryInfo(java.lang.String,java.lang.String,java.lang.String)]

SalaryQuery.vue:24 
GET http://localhost:8080/pmsys/employee/salaryInfo?id=5&salaryYear=2025&salaryMonth=05 400 (Bad Request)

```

解决:

后端的请求参数解析的注解用错了,不是@RequestBody,而是@RequestParam

```
 @GetMapping("/employee/salaryInfo")
    public Result getSalaryInfo(@RequestParam String id, @RequestParam String salaryYear, @RequestParam String salaryMonth){
        ...
    }
```



# 08前后端数据不一致,导致前端的数据没有正确绑定

后端返回的数据:

```
{
    "code": 200,
    "message": "获取用户工资信息成功",
    "data": {
        "id": 1,
        "employeeId": 5,
        "basicSalary": 30000.00,
        "positionSalary": 10000.00,
        "performanceSalary": 5000.00,
        "bonus": 20000.00,
        "insurance": 5000.00,
        "tax": 4500.00,
        "actualSalary": 55500.00,
        "salaryMonth": "2025-05",
        "status": true,
        "createTime": "2025-05-27T02:04:37.000+00:00",
        "updateTime": "2025-05-27T02:04:37.000+00:00"
    }
}
```

前端的数据绑定:

```
salaryInfo.basic_salary
salaryInfo.position_salary
...
```

解决:

前端的字段换成驼峰命名

# 09前端条件判断错误,导致没有正确赋值

嵌套

```
if (data.data.code === 200) {   //错误❌
        salaryInfo.value = data.data.data 
      } else {
        alert(data.data.message)
      }
```

解决:

```
if (data.code === 200) {   
        salaryInfo.value = data.data 
      } else {
        alert(data.data.message)
      }
```



10缺少@RequestBody,导致后端无法从请求体中获取HrDepartment

```
前端
{
    "timestamp": "2025-05-29T00:51:17.443+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/pmsys/department/add"
}

后端debug排错❌:
对象值为null
HrDepartment(id=null, deptName=null, deptCode=null, parentId=null, parentName=null, leaderId=null, leaderName=null, description=null, createTime=null, updateTime=null)
```

解决:

```
    @PostMapping("/department/add")
    public Result addDepartment(@RequestBody HrDepartment department) {  //  @RequestBody 不要忘记
        ...
    }
```

# 10异步请求处理不完整

```
添加成功和失败后,前端页面没有相应的弹窗提示
请求没有进行异步处理
```

解决:加async异步请求和await接受Promise对象

```
async function addDepartment(){
    let {data} = await axios.post('/department/add',department)
    if(data.code == 200){
      alert('添加成功')
    }else{
      alert('添加失败,请检查输入是否正确')
      department.value = {} //清空数据
    }
  }
```



# 11添加部门时,全null数据仍然可以被添加

解决:

在前端发送请求前,进行数据校验,若为null,则取消发送

```
    if (!department.deptName?.trim()  || 
        !department.deptCode?.trim()  || 
        !department.description?.trim())  {
      alert('部门名称、编号和描述为必填项')
      return 
    }
```



# 12错误的路由参数获取

通过路由router传递参数,但是在目标组件中获取方式错误

```
浏览器报错:EditDepartment.vue:14
Uncaught (in promise) TypeError: Cannot read properties of undefined (reading '$route')
at setup (EditDepartment.vue:14:23)
```

解决:

```
import {useRouter,useRoute } from 'vue-router'
let route = useRoute() //获取route对象
  const  id  = route.params.id  //获取路由参数  部门id
```



# 13在script中定义的响应式数据赋值时undefined

![image-20250529110916409](C:\Users\袁权\AppData\Roaming\Typora\typora-user-images\image-20250529110916409.png)

解决:

- 响应数据实际嵌套在data字段中，代码未正确解构

```
let res = await axios.get(`/department/${id}`) 
department.deptName  = res.data.data.deptName 
...
```



# 14 错误请求400,后端`@RequestParam`默认要求参数必填，若前端未传递任一参数会触发400错误

```
Finance.vue:27 
 GET http://localhost:8080/pmsys/finance/salarys?employeeId=&salaryYear=2025&salaryMonth=05 400 (Bad Request)
```

解决:

```
@GetMapping("/finance/salarys")
public Result getSalarys(
    @RequestParam(required = false) Long employeeId,  // 允许空值 
    @RequestParam(required = false)  String salaryYear,  // 允许空值 
    @RequestParam(required = false)  String salaryMonth) {  // 允许空值 
    // 服务层需增加对employeeId=null的查询逻辑 
}
```



# 15当三个参数都为空时,却显示没有查到数据,但是我是想要查询所有数据

前端接收到的结果:

```
{"code":500,"message":"获取员工工资信息失败,请检查查询参数","data":null}
```

显然问题出现在后端代码,通过debug发现:当年月份为空时,salary_month参数的拼接有误

```
 Preparing: SELECT * FROM hr_salary WHERE 1=1 AND salary_month = CONCAT( ?, '-', LPAD(?, 2, '0') )
 Parameters: (String), (String)
 注:CONCAT 最终生成 "0-00",导致没有查询到数据,经过逻辑判断,返回500错误
```

解决:

在 Controller 中增加空字符串转 `null` 的逻辑：

```
salaryYear = StringUtils.hasText(salaryYear)  ? salaryYear : null;
salaryMonth = StringUtils.hasText(salaryMonth)  ? salaryMonth : null;
```



# 16级联查询sql语句结果缺失

employeeName和employeeNo始终为null,说明sql语句有缺陷

```
{
    "code": 200,
    "message": "获取已完成的调动列表成功",
    "data": [
        {
            "id": 1,
            "employeeId": 5,
            "employeeName": null,
            "beforeDepartmentId": 6,
            "beforeDepartmentName": "技术一部",
            "afterDepartmentId": 7,
            "afterDepartmentName": "技术二部",
            "beforePositionId": 6,
            "beforePositionName": "高级工程师",
            "afterPositionId": 6,
            "afterPositionName": "高级工程师",
            "transferType": true,
            "transferReason": "部门业务调整",
            "transferDate": "2025-02-15",
            "approverId": 2,
            "approverName": "张三",
            "status": 1,
            "createTime": "2025-05-27T02:07:36.000+00:00",
            "updateTime": "2025-05-27T02:07:36.000+00:00"
        }
        ...
    ]
}
```

解决:

不同表的id不同,注意对应

```
  <select id="selectCompletedTransferList" resultType="com.hnust.personnelmanagement.bean.dto.HrTransfer">
    SELECT
      ht.*,
      u.real_name  AS employee_name,
      (SELECT dept_name FROM hr_department WHERE id = ht.before_department_id)  AS before_department_name,
      (SELECT dept_name FROM hr_department WHERE id = ht.after_department_id)  AS after_department_name,
      (SELECT position_name FROM hr_position WHERE id = ht.before_position_id)  AS before_position_name,
      (SELECT position_name FROM hr_position WHERE id = ht.after_position_id)  AS after_position_name,
      u.real_name  AS approver_name
    FROM hr_transfer ht
           LEFT JOIN hr_employee e ON ht.employee_id  = e.employee_no
           LEFT JOIN sys_user u ON ht.employee_id  = u.id
    WHERE ht.status  = 1
    ORDER BY ht.transfer_date  ASC
  </select>
```



# 17错误400,前端发送post请求,将参数放在请求体,后端应该用一个对象来接收

```
TransferNotApply.vue:31 
 POST http://localhost:8080/pmsys/transfer/approvalOrNot 400 (Bad Request)
```

解决:

后端定义一个dto类,用于接收前端的数据

```
package com.hnust.personnelmanagement.bean.dto;

import lombok.Data;

@Data
public class ApprovalTransfer {
    private Long id;
    private int status;
}
```

```
 @PostMapping("/approvalOrNot")
    public Result approvalOrNot(@RequestBody ApprovalTransfer approvalTransfer) {
       ...
    }
```



# 18查询已审批调动列表时,不仅要查通过的,拒绝的也要查

解决:

在sql语句中加一个或条件

```
<!--  查询已审批的调动-->
  <select id="selectCompletedTransferList" resultType="com.hnust.personnelmanagement.bean.dto.HrTransfer">
    SELECT
      ht.*,
      u.real_name  AS employee_name,
      (SELECT dept_name FROM hr_department WHERE id = ht.before_department_id)  AS before_department_name,
      (SELECT dept_name FROM hr_department WHERE id = ht.after_department_id)  AS after_department_name,
      (SELECT position_name FROM hr_position WHERE id = ht.before_position_id)  AS before_position_name,
      (SELECT position_name FROM hr_position WHERE id = ht.after_position_id)  AS after_position_name,
      u.real_name  AS approver_name
    FROM hr_transfer ht
           LEFT JOIN hr_employee e ON ht.employee_id  = e.employee_no
           LEFT JOIN sys_user u ON ht.employee_id  = u.id
    WHERE ht.status  = 1 or ht.status  = 2
    ORDER BY ht.transfer_date  ASC
  </select>
```



# 19系统管理员删除员工数据时,级联删除该用户的所有数据

解决方案,使用事务,保证原子性:

```
<!--    根据id删除sys_user中的员工信息,同时删除该用户在其他表中的数据-->
    <delete id="deleteUserById">
        <!-- 开启事务执行多语句操作 -->
        SET autocommit = 0;
        START TRANSACTION;

        <!-- 1. 删除用户角色关联 -->
        DELETE FROM sys_user_role WHERE user_id = #{id};

        <!-- 2. 获取关联的员工ID -->
        SET @employeeId = (SELECT e.id
        FROM hr_employee e
        JOIN sys_user u ON u.real_name  = e.name  AND u.phone  = e.phone
        WHERE u.id  = #{id});

        <!-- 3. 更新部门负责人 -->
        UPDATE hr_department SET leader_id = NULL
        WHERE leader_id = #{id};

        <!-- 4. 删除薪酬记录 -->
        DELETE FROM hr_salary
        WHERE employee_id = #{id};

        <!-- 5. 删除人事调整记录 -->
        DELETE FROM hr_transfer
        WHERE employee_id = #{id};

        <!-- 6. 删除员工信息 -->
        DELETE FROM hr_employee
        WHERE id = @employeeId;

        <!-- 7. 删除用户 -->
        DELETE FROM sys_user
        WHERE id = #{id};

        COMMIT;
        SET autocommit = 1;
    </delete>
```



# 20系统管理员删除员工数据时,AdminController的结果判断

删除成功,但是前端显示500错误,操作失败,分析原因就在于res结果判断

```
Preparing: SET autocommit = 0; START TRANSACTION; DELETE FROM sys_user_role WHERE user_id = ?; SET @employeeId = (SELECT e.id FROM hr_employee e JOIN sys_user u ON u.real_name = e.name AND u.phone = e.phone WHERE u.id = ?); UPDATE hr_department SET leader_id = NULL WHERE leader_id = ?; DELETE FROM hr_salary WHERE employee_id = ?; DELETE FROM hr_transfer WHERE employee_id = ?; DELETE FROM hr_employee WHERE id = @employeeId; DELETE FROM sys_user WHERE id = ?; COMMIT; SET autocommit = 1;

Parameters: 7(Long), 7(Long), 7(Long), 7(Long), 7(Long), 7(Long)
Updates: 0
```

解决: 再查询一次

```
	//修改前
    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUserById(@PathVariable Long id){
        int res = adminService.deleteUserById(id);
        if (res!=0)
            return Result.success("删除用户成功", null);
        return Result.error(500,"删除用户失败");
    }
    
    //修改后
    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUserById(@PathVariable Long id) {
            adminService.deleteUserById(id);
            if( adminService.getUserById(id))
                 return Result.error(500, "删除用户失败");
            return Result.success("删除用户成功", null);
    }
```



# 21请求拦截守卫

```
// 全局前置守卫
router.beforeEach((to, from, next) => {
  // 如果访问的是登录页面或根路径，直接放行
  if (to.path === '/login' || to.path === '/') { //错误写法:if (to.path === '/login' || / ) 始终为true,拦截不起作用
    next()
    return
  }

  // 检查用户是否已登录
  if (userStore.realName === '') {  //只要登陆了,就会保存用户的基本信息
    // 弹窗提示
    alert('您尚未登录，请先登录')
    // 重定向到登录页面
    next('/login')
    return
  }
  
  // 已登录，放行
  next()
})
```



# 22router.js中的store初始化问题以及pinia导入

```
Uncaught Error: [🍍]: "getActivePinia()" was called but there was no active Pinia. Are you trying to use a store before calling "app.use(pinia)"?

See https://pinia.vuejs.org/core-concepts/outside-component-usage.html for help.

This will fail in production.

at useStore (pinia.mjs:1700:19)

at router.js:6:17
```

解决:

将store的初始化移到路由守卫内部，而不是在模块顶层

