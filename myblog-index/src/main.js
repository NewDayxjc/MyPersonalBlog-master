import ElementUI from 'element-ui';
import infiniteScroll from 'vue-infinite-scroll'
import 'element-ui/lib/theme-chalk/index.css';
// 引入样式文件
import './css/base.css';
import 'animate.css';
import 'font-awesome/css/font-awesome.min.css' //Vue.config.productionTip = false
import 'swiper/dist/css/swiper.css'
import Highlight from 'vue-markdown-highlight'
import VueLazyload from 'vue-lazyload'
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.use(Highlight)
Vue.use(ElementUI)
Vue.use(infiniteScroll)
Vue.use(VueLazyload)
 

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
 
