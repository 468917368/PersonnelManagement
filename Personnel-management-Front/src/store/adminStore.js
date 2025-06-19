import { defineStore } from "pinia";

export const defineUserList = defineStore(
    "userList",
    {
        state:()=>{
            return {
                userList:[
                    /* {
                        "id": 1001,
                        "username": "john_doe",
                        "real_name": "张三",
                        "email": "john@example.com", 
                        "phone": "13800138000",
                        "status": 1,
                        "status_label": "正常",
                        "create_time": "2024-05-30T10:30:00",
                        "update_time": "2025-05-28T15:20:00"
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


export const defineUser = defineStore(
    "user",
    {
        state:()=>{
            return {
                userInfo:{
                    /* {
                       "username": "zhangsan",
                        "password": "zhangsan123",
                        "email": "zhangsan@company.com", 
                        "phone": "13800138000",
                        "realName": "张三"
                      } */
                },
                employeeInfo:{
                    /* {
                        "employeeNo": "EMP003",
                        "gender": 1,
                        "birthDate": "1995-08-12",
                        "idCard": "110101199508123456",
                        "address": "北京市朝阳区",
                        "education": "硕士研究生",
                        "positionId": 3,
                        "departmentId": 3,
                        "entryDate": "now()"
                    } */
                },
                roleId : ''
            }
        },
        getters:{

        },
        actions:{
            
        }
    }
)