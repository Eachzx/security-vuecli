import Vue from 'vue'
import App from './App.vue'
import router from '@/router/index'
import store from '@/store/store'
import config from '@/config'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import echarts from 'echarts'


Vue.use(iView);
Vue.prototype.$config = config;
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts 



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
