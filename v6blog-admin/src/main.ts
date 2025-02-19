import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import { VueQueryPlugin } from '@tanstack/vue-query'

import { createNotivue } from 'notivue'
import 'notivue/notification-progress.css'
import 'notivue/notification.css' // Only needed if using built-in notifications
import 'notivue/animations.css' // Only needed if using built-in animations

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.use(VueQueryPlugin, {
  enableDevtoolsV6Plugin: true,
})

const notivue = createNotivue({
  avoidDuplicates: true,
  
})
app.use(notivue)

app.mount('#app')
