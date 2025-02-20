<script setup lang="ts">
import VInput from '@/components/VInput.vue'
</script>

<template>
  <div id="login-card" class="card w-full h-full lg:card-side bg-base-100 shadow-sm">
    <figure class="flex-5">
      <img
        src="https://img.daisyui.com/images/stock/photo-1494232410401-ad00d5433cfa.webp"
        alt="Album"
      />
    </figure>
    <div class="card-body flex-3">
      <h1 class="card-title flex justify-center">
        v6blog Admin
        <span class="badge badge-primary">登录</span>
      </h1>
      <form class="flex flex-col items-center gap-4">
        <VInput />
      </form>
    </div>
  </div>
</template>

<style scoped></style>
