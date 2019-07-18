import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/views/index'
import Detail from '@/views/detail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/detail',
      name: 'detail',
      component: Detail
    }
  ]
})
