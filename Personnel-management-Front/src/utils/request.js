/* 
    创建一个axios实例
*/

import axios from 'axios'

    let instance=axios.create({
        baseURL:'http://localhost:8080/pmsys'
    }
    )

    //请求拦截器
    instance.interceptors.request.use(
        (config)=>{
            return config
        },
        (error)=>{
            return Promise.reject(error)
        }
    )

    //响应拦截器
    instance.interceptors.response.use(
        (response)=>{
            return response
        },
        (error)=>{
            return Promise.reject(error)
        }
    )



    export default instance