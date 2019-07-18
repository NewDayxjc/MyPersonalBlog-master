import axios from 'axios';
axios.defaults.withCredentials=true;
import baseURL from '@/constants/index'
let base = baseURL.ONLINEURL+'/user';
axios({
  withCredentials: true
})
export const loginUser = params =>{ return axios.post(`${base}/login`,params)}

export const checkLogin = params =>{ return axios.get(`${base}/checkLogin`,params)}

export const isLogout = params =>{ return axios.get(`${base}/logout`,params)}


