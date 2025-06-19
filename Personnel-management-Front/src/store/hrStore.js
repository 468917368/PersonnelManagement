import {defineStore} from 'pinia'

export const defineTransferList = defineStore(
    "transferList",  //id
    {
        state:()=>{
            return {
                transferList:[
                    //从后端数据库返回数据,赋值给pinia
                  /* {
                        "code": 200,
                        "message": "操作成功",
                        "data": {
                            "total": 100,
                            "list": [
                            {
                                "id": 1,
                                "employeeId": 1,
                                "employeeName": "张三",
                                "beforeDepartmentName": "技术部",
                                "afterDepartmentName": "产品部",
                                "beforePositionName": "Java工程师",
                                "afterPositionName": "产品经理",
                                "transferType": 3,
                                "transferReason": "岗位调整",
                                "transferDate": "2023-01-01",
                                "status": 1,
                                "createTime": "2022-12-25 00:00:00"
                            }
                            ]
                        }
                    },
                     {
                        "code": 200,
                        "message": "操作成功",
                        "data": {
                            "total": 100,
                            "list": [
                            {
                                "id": 1,
                                "employeeId": 1,
                                "employeeName": "张三",
                                "beforeDepartmentName": "技术部",
                                "afterDepartmentName": "产品部",
                                "beforePositionName": "Java工程师",
                                "afterPositionName": "产品经理",
                                "transferType": 3,
                                "transferReason": "岗位调整",
                                "transferDate": "2023-01-01",
                                "status": 1,
                                "createTime": "2022-12-25 00:00:00"
                            }
                            ]
                        }
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
