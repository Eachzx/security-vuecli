import Vue from 'vue'
import Router from 'vue-router'
import routes from './router'
import iView from 'iview'
import store from '@/store/store'
import {initMenu} from '@/libs/utils.js'
import localstorages from '@/libs/localstorage.js'
Vue.use(Router)


const router=new Router({
    mode: 'history',
    routes,

})


const LOGIN_PAGE_NAME = 'login';
router.beforeEach((to, from, next) => {
  let token=localstorages.get('token')
    iView.LoadingBar.start();
      if(!token && to.name != LOGIN_PAGE_NAME){
        //未登录且跳转的是其他页面
        localstorages.clear();
        store.state.app.tabList=[]
        next({
          name: LOGIN_PAGE_NAME // 跳转到登录页
        })  
      }else if (!token && to.name === LOGIN_PAGE_NAME) {
        // 未登录且跳转的时登录页
        localstorages.clear();
        next()
      }else if (token && to.name === LOGIN_PAGE_NAME) {
        // 已登录且要跳转的页面是登录页
        if(store.state.app.menuList.length ==0){
          initMenu(router, store);
          next({
            path: '/home' // 跳转到homeName页
          })
        }
      }else if(token){
      // 已登录且要跳转的页面是其他页面
        if(store.state.app.menuList.length ==0){
          initMenu(router, store);
          let tabs=localstorages.get('tabList')
          if (tabs) {
          store.dispatch('updateTabList',tabs)
          }
          next({
            path:to.path
          })
        }
      }
        next()
      })


export default router