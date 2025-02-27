<script setup lang="ts">
import { Icon } from '@iconify/vue'
import { useField } from 'vee-validate'
import { computed, onMounted, ref, watch, type InputTypeHTMLAttribute } from 'vue'

// 定义组件的 props 接口
interface Props {
  id?: string
  name: string
  class?: string
  width?: string
  height?: string
  type?: InputTypeHTMLAttribute
  placeholder?: string
  title?: string
  readonly?: boolean
  icon?: string
  iconSize?: string
}

// 获取组件的 props
const props = defineProps<Props>()

// 定义一个变量来存储 input 元素
const inputRef = ref<HTMLInputElement>()

// 在组件挂载时获取 input 元素，
onMounted(() => {
  const ele = inputRef.value

  watch(
    errorMessage,
    () => {
      console.error(errorMessage.value)
      if (ele) {
        if (errorMessage.value) {
          ele.setCustomValidity(errorMessage.value)
          ele.blur()
          ele.focus()
        } else {
          ele.setCustomValidity('')
        }
      }
    },
    {
      immediate: true,
    },
  )
})

// 基础功能的组合函数
function useBase() {
  // 使用 vee-validate 的 useField 钩子来管理表单字段的值和错误信息
  const { value, errorMessage, errors } = useField(() => props.name, undefined)

  // 监听错误信息的变化，并触发user-invalid

  // 定义一个本地类型的 ref，用于动态切换 input 类型
  const localType = ref<InputTypeHTMLAttribute>()

  // 计算实际的 input 类型，优先使用本地类型
  const realType = computed(() => {
    return localType.value || props.type
  })

  return {
    props,
    value,
    errorMessage,
    errors,
    localType,
    realType,
  }
}

// 密码功能的组合函数
function usePassword() {
  // 定义一个 ref 来管理密码可见性
  const passwordVisible = ref(false)

  // 切换密码可见性的函数
  const togglePasswordVisible = () => {
    passwordVisible.value = !passwordVisible.value
    console.log(passwordVisible.value)

    // 根据密码可见性切换 input 类型
    if (passwordVisible.value) {
      localType.value = 'text'
    } else {
      localType.value = 'password'
    }
  }

  return {
    passwordVisible,
    togglePasswordVisible,
  }
}

// 使用基础功能组合函数
const { value, errorMessage, localType, realType } = useBase()

// 使用密码功能组合函数
const { passwordVisible, togglePasswordVisible } = usePassword()
</script>

<template>
  <div class="input-container w-xs m-0 p-0">
    <!-- 输入框容器 -->
    <label
      class="input validator m-0"
      :class="[props.class]"
      :style="{ width: width, height: height }"
    >
      <!-- 图标 -->
      <Icon v-if="icon" :icon="icon" :width="iconSize" :height="iconSize" />
      <!-- 输入框 -->
      <input
        :id="id || name"
        :name="name"
        :type="realType"
        v-model="value"
        :placeholder="placeholder"
        :title="title"
        :readonly="readonly"
        ref="inputRef"
      />
      <!-- 密码可见性切换图标 -->
      <Icon
        v-if="type === 'password'"
        :icon="passwordVisible ? 'line-md:watch-loop' : 'line-md:watch-off-loop'"
        :width="iconSize"
        :height="iconSize"
        @click="togglePasswordVisible"
        class="cursor-pointer"
      />
      <!-- 占位图标 -->
      <Icon v-else icon="" :width="iconSize" :height="iconSize" />
    </label>
    <!-- 错误信息提示 -->
    <p class="validator-hint h-4 w-xs">{{ errorMessage }}</p>
  </div>
</template>

<style scoped></style>
