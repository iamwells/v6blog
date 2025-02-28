<script setup lang="ts">
import VInput from '@/components/VInput.vue'
import { useForm, configure } from 'vee-validate'
import * as z from 'zod'
import { toTypedSchema } from '@vee-validate/zod'
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'

configure({
  validateOnInput: true,
})

const router = useRouter()

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
          /^([a-z]+)([a-z0-9_]*)$/,
          '用户名只能包含小写字母、数字和下划线，且必须以字母开头！',
        ),
      password: z
        .string({
          required_error: '密码不能为空！',
        })
        .nonempty('密码不能为空！')
        .min(6, '密码长度6-32位！')
        .max(32, '密码长度6-32位！')
        .regex(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).+$/, '密码必须包含大写字母、小写字母、数字！'),
    }),
  ),
})

const { login } = useAuthStore()

const onSubmit = handleSubmit(async (values, ctx) => {
  const { username, password } = values
  console.log(ctx)
  login(username, password).then(() => {
    router.push({ path: '/' })
  })
  console.log('submit', values)
})
</script>

<template>
  <form
    id="login-form"
    class="flex flex-col justify-center items-center gap-4"
    @submit.prevent="onSubmit"
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
      icon="line-md:security"
    />
    <button class="btn w-xs" type="submit">登录</button>
  </form>
</template>

<style scoped></style>
