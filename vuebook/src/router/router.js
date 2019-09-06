import test from "_c/test";



export default [
    {
      path:'/login',
      name:'login',
      component:()=>import('@/views/login/index')
    },
    {
      path: '/',
      name: 'main',
      component: () => import('@/views/main/index.vue'),
      children:[
        {
          path: '/home',
      name: 'home',
      component: () => import('_c/home'),
        },
      ]
    },
  ]
