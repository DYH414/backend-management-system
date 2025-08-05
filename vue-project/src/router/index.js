import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/Manager',
      component: () => import('../views/Manager.vue'),
      children:[
          {
            path:'Home',
            name: 'Home',
            meta:{
              title:'Home'
            },
            component: () => import('../views/Home.vue'),
          },
        {
           path: 'Test',
           name: 'Test',
          meta:{
            title:'Test'
          },
          component: () => import('../views/Test.vue'),
        },
        {
          path: 'Data',
          name: 'Data',
          meta:{
            title:'Data'
          },
          component: () => import('../views/Data.vue'),
        },
        {
           path: 'Employee',
          name: 'Employee',
          meta:{
            title:'员工信息'
          },
          component: () => import('../views/Employee.vue' ),
        },

      ]
    },
    {
      path: '/',
     redirect: '/Manager/Home',
      component: () => import('../views/Home.vue'),
    },
      {
      path:'/Login',
      name:'Login',
      component: () => import('../views/Login.vue'),
      meta:{
        title:'Login'
      }
    },
      {
      path:'/Register',
      name:'Register',
      component: () => import('../views/Register.vue'),
      meta:{
        title:'Register'
      }
    },

    {
      path:'/:pathMatch(.*)',
      name:'404',
      component: () => import('../views/404.vue'),
      meta:{
        title:'404 not found'
      }
    },
    {
      path:'/:pathMatch(.*)',
      redirect: '/404',
      meta:{
        title:'404 not found'
      }
    }



  ],
})
router.beforeEach((to, from, next) => {
  document.title=to.meta.title
  next()
})
export default router
