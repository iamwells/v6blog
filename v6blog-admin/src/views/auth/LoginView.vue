<script setup lang="ts">
import VInput from '@/components/VInput.vue'
import { ref } from 'vue'
import { useForm, configure } from 'vee-validate'
import * as z from 'zod'
import { toTypedSchema } from '@vee-validate/zod'
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'

configure({
  validateOnInput: true,
})
const formRef = ref(null)
// const username = ref('123')
const { handleSubmit } = useForm({
  validationSchema: toTypedSchema(
    z.object({
      username: z
        .string({
          required_error: '用户名不能为空！',
        })
        .nonempty('用户名不能为空！')
        .min(2, '用户名长度2-32位！')
        .max(32, '用户名长度2-32位！')
        .regex(
          /^([a-zA-Z]+)([a-zA-Z0-9_]*)$/,
          '用户名只能包含字母、数字和下划线，且必须以字母开头！',
        ),
      password: z
        .string({
          required_error: '密码不能为空！',
        })
        .nonempty('密码不能为空！')
        .min(6, '密码长度6-32位！')
        .max(32, '密码长度6-32位！')
        .regex(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,}$/, '密码必须包含大写字母、小写字母、数字！'),
    }),
  ),
})

const router = useRouter()
const { login } = useAuthStore()

const onSubmit = handleSubmit(async (values) => {
  console.error('submit')
  const { username, password } = values
  login(username, password).then(() => {
    router.push({ path: '/' })
  })
  console.log('submit', values)
})
</script>

<template>
  <div id="login-card" class="card w-full h-full lg:card-side bg-base-100 shadow-sm">
    <figure class="flex-3">
      <img
        src="https://img.daisyui.com/images/stock/photo-1494232410401-ad00d5433cfa.webp"
        alt="Album"
      />
    </figure>
    <div class="card-body flex-1 flex justify-center">
      <h1 class="card-title flex justify-center">
        v6blog Admin
        <span class="badge badge-primary">登录</span>
      </h1>
      <form
        class="flex flex-col items-center gap-4 justify-center"
        @submit.prevent="onSubmit"
        ref="formRef"
      >
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
        <button class="btn w-xs" type="submit">登录</button>
      </form>
    </div>
  </div>
</template>

<style scoped></style>
