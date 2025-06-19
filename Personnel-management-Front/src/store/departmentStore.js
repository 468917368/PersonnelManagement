import {defineStore} from 'pinia'

export const defineDepartment = defineStore(
    "departmentList",  //id
    {
        state:()=>{
            return {
                departmentList:[
                    //从后端数据库返回数据,赋值给pinia
                  /*{
                            "id": 1,
                            "deptName": "技术研发部",
                            "deptCode": "TECH_001",
                            "parentId": 0,
                            "leaderId": 1001,
                            "description": "负责产品研发和技术创新",
                            "createTime": "2025-05-20T08:30:00",
                            "updateTime": "2025-05-25T14:15:00",
                            "leaderName": "张伟",       // 需要关联查询时添加 
                    },
                    {
                            "id": 1,
                            "deptName": "技术研发部",
                            "deptCode": "TECH_001",
                            "parentId": 0,
                            "leaderId": 1001,
                            "description": "负责产品研发和技术创新",
                            "createTime": "2025-05-20T08:30:00",
                            "updateTime": "2025-05-25T14:15:00",
                            "leaderName": "张伟",       // 需要关联查询时添加 
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
