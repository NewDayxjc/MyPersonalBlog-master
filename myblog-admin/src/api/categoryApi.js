import axios from 'axios';
import baseURL from '@/constants/index'
axios.defaults.withCredentials=true;
let base = baseURL.ONLINEURL+'/category';

export const getCategoryList = params =>{ return axios.get(`${base}/find`,{params:params})}
