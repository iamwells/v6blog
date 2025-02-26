<script setup lang="ts">
import { Icon } from '@iconify/vue'

import { useField } from 'vee-validate'
import { onMounted, watch, type InputTypeHTMLAttribute } from 'vue'

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
  console.log(ele)
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
</script>

<template>
  <div>
    <label class="input validator" :class="[props.class]" :style="{ width: width, height: height }">
      <Icon v-if="icon" :icon="icon" :width="iconSize" :height="iconSize" />
      <input
        :id="id || name"
        :name="name"
        :type="type"
        v-model="value"
        :placeholder="placeholder"
        :title="title"
        :readonly="readonly"
      />
    </label>
    <p v-if="errorMessage" class="validator-hint">{{ errorMessage }}</p>
  </div>
</template>

<style scoped></style>
