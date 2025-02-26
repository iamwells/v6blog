<script setup lang="ts">
import { Icon } from '@iconify/vue'

import { useField } from 'vee-validate'
import { computed, onMounted, ref, watch, type InputTypeHTMLAttribute } from 'vue'

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
let ele: HTMLInputElement
onMounted(() => {
  ele = document.querySelector(`input[name='${props.name}']`) as HTMLInputElement
})

const props = defineProps<Props>()

const { value, errorMessage } = useField(() => props.name, undefined)

watch(errorMessage, () => {
  console.error(errorMessage.value)
  if (errorMessage.value) {
    ele.setCustomValidity(errorMessage.value)
  } else {
    ele.setCustomValidity('')
  }
})

const passwordVisible = ref(false)
const localType = ref<InputTypeHTMLAttribute>()

const togglePasswordVisible = () => {
  passwordVisible.value = !passwordVisible.value
  console.log(passwordVisible.value)

  if (passwordVisible.value) {
    localType.value = 'text'
  } else {
    localType.value = 'password'
  }
}

const realType = computed(() => {
  return localType.value || props.type
})
</script>

<template>
  <div>
    <label class="input validator" :class="[props.class]" :style="{ width: width, height: height }">
      <Icon v-if="icon" :icon="icon" :width="iconSize" :height="iconSize" />
      <input
        :id="id || name"
        :name="name"
        :type="realType"
        v-model="value"
        :placeholder="placeholder"
        :title="title"
        :readonly="readonly"
      />
      <Icon
        v-if="type === 'password'"
        :icon="passwordVisible ? 'line-md:watch-loop' : 'line-md:watch-off-loop'"
        :width="iconSize"
        :height="iconSize"
        @click="togglePasswordVisible"
        class="cursor-pointer"
      />
      <Icon v-else icon="" :width="iconSize" :height="iconSize" />
    </label>
    <p class="validator-hint">{{ errorMessage }}</p>
  </div>
</template>

<style scoped></style>
