import { defineStore } from "pinia";

export const defineUser = defineStore(
    "loginUser",
    {
        state:()=>{
            return {
                id:'',
                username:'',
                realName:'',
                roleCode:''
            }
        },
        getters:{

        },
        actions:{
            
        }
    }
)