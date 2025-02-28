<script setup lang="ts">
import VInput from '@/components/VInput.vue'
import { toTypedSchema } from '@vee-validate/zod'
import { useForm, configure } from 'vee-validate'
import { ref } from 'vue'
import * as z from 'zod'

configure({
  validateOnInput: true,
})
const { handleSubmit } = useForm({
  validationSchema: toTypedSchema(
    z
      .object({
        username: z
          .string({
            required_error: '用户名不能为空！',
          })
          .nonempty('用户名不能为空！')
          .min(2, '用户名长度2-32位！')
          .max(32, '用户名长度2-32位！')
          .regex(/^([a-z]+)([a-z0-9_]*)$/, '用户名只能包含字母、数字和下划线，且必须以字母开头！'),
        nickname: z
          .string({
            required_error: '昵称不能为空！',
          })
          .nonempty('昵称不能为空！')
          .min(2, '昵称长度2-32位！')
          .max(32, '昵称长度2-32位！')
          .regex(/^[\u4E00-\u9FA5A-Za-z0-9_]+$/, '昵称只能包含中文、字母、数字和下划线！'),
        password: z
          .string({
            required_error: '密码不能为空！',
          })
          .nonempty('密码不能为空！')
          .min(6, '密码长度6-32位！')
          .max(32, '密码长度6-32位！')
          .regex(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).+$/, '密码必须包含大写字母、小写字母、数字！'),
        password_confirm: z
          .string({
            required_error: '密码不能为空！',
          })
          .nonempty('密码不能为空！')
          .min(6, '密码长度6-32位！')
          .max(32, '密码长度6-32位！')
          .regex(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).+$/, '密码必须包含大写字母、小写字母、数字！'),
        email: z
          .string({
            required_error: '邮箱不能为空！',
          })
          .email('邮箱格式不正确！'),
      })
      .refine((data) => data.password === data.password_confirm, {
        message: '两次密码输入不一致！',
        path: ['password_confirm'],
      }),
  ),
})
const verifyModal = ref<HTMLDialogElement>()

const onSubmit = handleSubmit(
  async (values) => {
    if (verifyModal.value) {
      verifyModal.value.showModal()
      console.log('submit', values)
    }
  },
  ({ errors }) => {
    if (Object.keys(errors).length === 1 && errors.email && verifyModal.value) {
      console.log('submit!!!', errors)

      verifyModal.value.showModal()
    }
  },
)
</script>

<template>
  <div>
    <form
      id="register-form"
      class="flex flex-col justify-center items-center gap-4"
      @submit.prevent="onSubmit"
    >
      <VInput
        name="username"
        type="text"
        class="input-xl"
        placeholder="请输入用户名"
        icon="line-md:account"
        label="用户名"
      />

      <VInput
        name="nickname"
        type="text"
        class="input-xl"
        placeholder="请输入昵称"
        icon="line-md:emoji-smile"
        label="昵称"
      />

      <VInput
        name="password"
        type="password"
        class="input-xl"
        placeholder="请输入密码"
        icon="line-md:security"
        label="密码"
      />
      <VInput
        name="password_confirm"
        type="password"
        class="input-xl"
        placeholder="请再次输入密码"
        icon="line-md:security-twotone"
        label="密码确认"
      />
      <button class="btn w-xs" type="submit">注册</button>
      <dialog ref="verifyModal" class="modal modal-bottom sm:modal-middle">
        <div class="modal-box">
          <h3 class="text-lg font-bold">注册验证</h3>
          <p class="py-4">请输入邮箱进行验证！</p>
          <div class="flex flex-row items-start gap-4">
            <VInput
              name="email"
              type="email"
              class="input-xl"
              placeholder="请输入邮箱"
              icon="line-md:email"
            />
            <button class="btn h-14 flex-1" :disabled="true">发送验证</button>
          </div>
          <div class="modal-action">
            <form method="dialog">
              <!-- if there is a button in form, it will close the modal -->
              <button class="btn">Close</button>
            </form>
          </div>
        </div>
      </dialog>
    </form>
  </div>
</template>

<style scoped></style>
