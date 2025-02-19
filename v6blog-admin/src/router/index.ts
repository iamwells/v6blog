import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '@/views/DashboardView.vue'
import AuthView from '@/views/AuthView.vue'

import nProgress from '@/utils/nprogress'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '',
      name: 'dashboard',
      component: DashboardView,
      redirect: 'overview',
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
      name: 'auth',
      component: AuthView,
    },
  ],
})

router.beforeEach((to, from, next) => {
  nProgress.start()
  next()
})

router.afterEach(() => {
  nProgress.done()
})

export default router
