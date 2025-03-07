import { createRouter, createWebHistory } from 'vue-router'
import AdminLayout from '@/layouts/admin/AdminLayout.vue'
import SignLayout from '@/layouts/sign/SignLayout.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/admin/dashboard',
    },
    {
      path: '/admin',
      component: AdminLayout,
      redirect: '/admin/dashboard',
      children: [
        {
          path: 'dashboard',
          name: 'dashboard',
          component: () => import('@/views/admin/DashBoardView.vue'),
          meta: {
            title: '仪表盘 | v6blog admin',
            menuName: '仪表盘',
            icon: 'dashboard',
          },
        },
        {
          path: 'test',
          name: 'test',
          component: () => import('@/views/admin/TestView.vue'),
          meta: {
            title: '测试 | v6blog admin',
            menuName: '测试',
            icon: 'test',
          },
        },
      ],
    },
    {
      path: '/sign',
      component: SignLayout,
      redirect: '/sign/in',
      children: [
        {
          path: 'in',
          name: 'signin',
          component: () => import('@/views/sign/SignInView.vue'),
          meta: {
            title: '登录 | v6blog admin',
          },
        },
        {
          path: 'up',
          name: 'signup',
          component: () => import('@/views/sign/SignUpView.vue'),
          meta: {
            title: '注册 | v6blog admin',
          },
        },
      ],
    },
  ],
})

export default router
