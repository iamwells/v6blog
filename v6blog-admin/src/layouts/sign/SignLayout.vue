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
  <div v-if="bgImg" id="sign-layout" class="flex flex-col h-screen lg:flex-row">
    <div
      id="sign-layout-pic"
      :style="{ backgroundImage: `url(${bgImg})` }"
      class="flex-1 lg:flex-3 transition-all no-repeat bg-cover"
    ></div>
    <div id="sign-layout-content" class="flex-3 lg:flex-1 transition-all">
      <RouterView />
    </div>
  </div>
</template>

<style scoped></style>
