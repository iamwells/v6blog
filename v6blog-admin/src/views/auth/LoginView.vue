<script setup lang="ts">
import VInput from '@/components/VInput.vue'
import { reactive, ref } from 'vue'
import { useForm } from 'vee-validate'
import * as z from 'zod'
import { toTypedSchema } from '@vee-validate/zod'

const formRef = ref(null)
// const username = ref('123')
const { handleSubmit, values, errors } = useForm({
  validationSchema: toTypedSchema(
    z.object({
      username: z.string().nonempty('username is required').min(3, 'at least 3').max(50, 'max 50'),
      password: z.string().nonempty('password is required').min(6, 'at least 6').max(50, 'max 50'),
    }),
  ),
  validateOnMount: true,
})
</script>

<template>
  <div id="login-card" class="card w-full h-full lg:card-side bg-base-100 shadow-sm">
    <figure class="flex-5">
      <img
        src="https://img.daisyui.com/images/stock/photo-1494232410401-ad00d5433cfa.webp"
        alt="Album"
      />
    </figure>
    <div class="card-body flex-3 flex justify-center">
      <h1 class="card-title flex justify-center">
        v6blog Admin
        <span class="badge badge-primary">登录</span>
      </h1>
      <form class="flex flex-col items-center gap-4" ref="formRef">
        <VInput
          name="username"
          type="text"
          class="input-xl"
          placeholder="用户名"
          icon="line-md:account"
        />

        <VInput
          name="password"
          type="password"
          class="input-xl"
          placeholder="密码"
          icon="ant-design:lock-outlined"
        />
        <button class="btn" type="submit">Submit form</button>
      </form>
      {{ values }}
      {{ errors }}
    </div>
  </div>
</template>

<style scoped></style>
