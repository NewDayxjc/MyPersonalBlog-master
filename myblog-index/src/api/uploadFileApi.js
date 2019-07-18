import axios from 'axios';
import baseURL from '@/constants/index'
axios.defaults.withCredentials=true;

let base = baseURL.ONLINEURL+'/upload';
axios({
    withCredentials: true
})
export const uploadFile = params =>{ return axios.post(`${base}/file`,params)}

export const uploadByBase64 = params =>{ return axios.post(`${base}/fileBase64`,{base64:params})}
