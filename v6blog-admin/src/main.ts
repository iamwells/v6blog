import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'

import { VueQueryPlugin } from '@tanstack/vue-query'

import { createNotivue } from 'notivue'
import 'notivue/notification-progress.css'
import 'notivue/notification.css' // Only needed if using built-in notifications
import 'notivue/animations.css' // Only needed if using built-in animations

const app = createApp(App)

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

const notivue = createNotivue({
  avoidDuplicates: true,
})

app
  .use(router)
  .use(pinia)
  .use(VueQueryPlugin, {
    enableDevtoolsV6Plugin: true,
  })
  .use(notivue)
  .mount('#app')
