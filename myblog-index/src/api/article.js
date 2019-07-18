import axios from 'axios';
axios.defaults.withCredentials=true;
import baseURL from '@/constants/index'
let base = baseURL.ONLINEURL+'/article';
axios({
    withCredentials: true
})
export const add = params =>{ return axios.post(`${base}/add`,params) }

export const getArticleAll = params =>{ return axios.get(`${base}/find`,{params:params}) }

export const getArticleOne = params =>{ return axios.get(`${base}/get`,{params:params}) }

export const updateArticle = params =>{ return axios.post(`${base}/update`,params)}

export const deleteArticle = params =>{ return axios.post(`${base}/delete`,params)}

