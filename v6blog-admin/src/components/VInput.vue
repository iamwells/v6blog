<script setup lang="ts">
import { Icon } from '@iconify/vue'

import { useField } from 'vee-validate'
import { onMounted, watch } from 'vue'

interface Props {
  name: string
  class?: string
  width?: string
  height?: string
  type?: 'text' | 'password' | 'email' | 'number'
  placeholder?: string
  title?: string
  readonly?: boolean
  minlength?: number
  maxlength?: number
  min?: number
  max?: number
  icon?: string
  iconSize?: string
  ifValid?: boolean
  required?: boolean
  pattern?: string
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
  }else{
    ele.setCustomValidity('')
  }
})
</script>

<template>
  <div>
    <label class="input validator" :class="[props.class]" :style="{ width: width, height: height }">
      <Icon v-if="icon" :icon="icon" :width="iconSize" :height="iconSize" />
      <input
        :id="name"
        :name="name"
        :type="type"
        v-model="value"
        :placeholder="placeholder"
        :title="title"
        :required="required"
        :minlength="minlength"
        :maxlength="maxlength"
        :min="min"
        :max="max"
        :pattern="pattern"
        :readonly="readonly"
      />
    </label>
    <p v-if="errorMessage" class="validator-hint">{{ errorMessage }}</p>
  </div>
</template>

<style scoped></style>
