<script setup lang="ts">
import VInput from '@/components/VInput.vue'
import { ref } from 'vue'

const formRef = ref(null)
const onSubmit = () => {
  console.log('onSubmit', (formRef.value as unknown as HTMLFormElement))
}
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
      <form class="flex flex-col items-center gap-4" @submit.prevent="onSubmit" ref="formRef">
        <VInput
          type="text"
          class="input-xl"
          placeholder="用户名"
          icon="line-md--account"
          icon-size="1.2em"
          :minlength="2"
          validatorHint="格式不对！"
          :if-valid="true"
          width="500px"
          height="80px"
        />
        <button class="btn" type="submit">Submit form</button>
      </form>

      <button class="btn" @click="onSubmit">Submit form</button>
    </div>
  </div>
</template>

<style scoped></style>
