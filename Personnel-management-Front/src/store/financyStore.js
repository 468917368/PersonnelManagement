import {defineStore} from 'pinia'

export const defineFinance = defineStore(
    "salaryList",  //id
    {
        state:()=>{
            return {
                salaryList:[
                    //从后端数据库返回数据,赋值给pinia
                  /* {
                        "id": 1,
                        "employeeId": 1,
                        "employeeName": "张三",
                        "employeeNo": "EMP001",
                        "departmentName": "技术部",
                        "positionName": "Java工程师",
                        "basicSalary": 10000.00,
                        "positionSalary": 5000.00,
                        "performanceSalary": 3000.00,
                        "bonus": 1000.00,
                        "insurance": 1000.00,
                        "tax": 500.00,
                        "actualSalary": 17500.00,
                        "salaryMonth": "2023-01",
                        "status": 1
                    },
                     {
                        "id": 1,
                        "employeeId": 1,
                        "employeeName": "张三",
                        "employeeNo": "EMP001",
                        "departmentName": "技术部",
                        "positionName": "Java工程师",
                        "basicSalary": 10000.00,
                        "positionSalary": 5000.00,
                        "performanceSalary": 3000.00,
                        "bonus": 1000.00,
                        "insurance": 1000.00,
                        "tax": 500.00,
                        "actualSalary": 17500.00,
                        "salaryMonth": "2023-01",
                        "status": 1
                    } */
                ]
            }
        },
        getters:{

        },
        actions:{
            
        }
    }
)
