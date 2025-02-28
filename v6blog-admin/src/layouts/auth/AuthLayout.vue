<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'

const bgImg = ref<string>('')
axios.get('/bing/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN').then((res) => {
  const { images } = res.data
  if (images && images.length > 0) {
    const { url } = images[0]
    console.log(url)
    bgImg.value = `https://www.bing.com${url}`
  }
})
</script>

<template>
  <div id="login-card" class="card w-full h-full lg:card-side bg-base-100 shadow-sm">
    <figure class="w-full h-full" :class="{ skeleton: !bgImg }">
      <img v-show="bgImg" :src="bgImg"/>
    </figure>
    <div class="card-body flex-1 justify-center">
      <h1 class="card-title flex justify-center">
        v6blog Admin
        <span class="badge badge-primary">{{ $route.meta?.badge }}</span>
      </h1>
      <div class="text-center p-1">
        <RouterLink v-if="$route.name === 'register'" to="/auth/login"
          >已有账号？立即登录</RouterLink
        >
        <RouterLink v-else-if="$route.name === 'login'" to="/auth/register"
          >没有账号？立即注册</RouterLink
        >
      </div>
      <RouterView v-slot="{ Component }">
        <template v-if="Component">
          <Transition
            mode="out-in"
            enter-active-class="animate__animated animate__zoomIn animate__faster"
            leave-active-class="animate__animated animate__zoomOut animate__faster"
          >
            <KeepAlive>
              <Suspense>
                <component :is="Component" />
              </Suspense>
            </KeepAlive>
          </Transition>
        </template>
      </RouterView>
    </div>
  </div>
</template>

<style scoped>
[v-cloak] {
  display: none !important;
}
</style>
