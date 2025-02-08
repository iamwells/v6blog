import axios, {
  type AxiosInstance,
  type AxiosResponse,
  type InternalAxiosRequestConfig,
  AxiosError,
} from 'axios'
import JSONBig from 'json-bigint'

// 创建 axios 实例
const instance: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api', // 基础 URL
  timeout: 10000, // 请求超时时间
  transformResponse: [(data) => {
    // 使用 json-bigint 解析响应数据
    try {
      return JSONBig.parse(data);
    } catch (error) {
      return data;
    }
  }],
  transformRequest: [(data) => {
    // 使用 json-bigint 序列化请求数据
    return JSONBig.stringify(data);
  }]
});

// 请求拦截器
instance.interceptors.request.use(
  (config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
    // 在发送请求之前做些什么
    // 例如添加 token 到 headers
    const token = localStorage.getItem('token')
    if (token) {

    }
    return config
  },
  (error: AxiosError): Promise<AxiosError> => {
    // 对请求错误做些什么
    return Promise.reject(error)
  },
)

// 响应拦截器
instance.interceptors.response.use(
  (response: AxiosResponse): AxiosResponse => {
    // 对响应数据做些什么
    return response
  },
  (error: AxiosError): Promise<AxiosError> => {
    // 对响应错误做些什么
    if (error.response && error.response.status === 401) {
      // 例如处理未授权错误
      console.error('未授权，请重新登录')
    }
    return Promise.reject(error)
  },
)

export default instance
