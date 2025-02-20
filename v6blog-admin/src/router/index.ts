import { createRouter, createWebHistory } from 'vue-router'
import AdminLayout from '@/layouts/admin/AdminLayout.vue'
import AuthLayout from '@/layouts/auth/AuthLayout.vue'

import nProgress from '@/utils/nprogress'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/admin',
    },
    {
      path: '/admin',
      component: AdminLayout,
      redirect: '/admin/overview',
      children: [
        {
          path: 'overview',
          name: 'overview',
          component: () => import('@/views/admin/OverviewView.vue'),
        },
      ],
    },
    {
      path: '/auth',
      component: AuthLayout,
      redirect: '/auth/login',
      children: [
        {
          path: 'login',
          name: 'login',
          component: () => import('@/views/auth/LoginView.vue'),
        },
      ],
    },
  ],
})

router.beforeEach((to, from, next) => {
  nProgress.start()
  console.log('to', to)
  if (to.fullPath.includes('/admin')) {
    const token = localStorage.getItem('token')
    if (!token) {
      next('/auth/login')
      return
    }
  }
  next()
})

router.afterEach(() => {
  nProgress.done()
})

export default router
