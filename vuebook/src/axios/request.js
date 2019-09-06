import axios from 'axios'
 // 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8888',
  timeout: 15000 // 请求超时时间
})

service.interceptors.response.use(
    function(response) {
      //请求正常则返回
      return Promise.resolve(response)
    },
    function(error) {
      
      return Promise.reject(error)
    }
  )

  export default service