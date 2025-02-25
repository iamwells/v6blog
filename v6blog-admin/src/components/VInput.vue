<script setup lang="ts">
import { computed, defineModel } from 'vue'
import { Icon } from "@iconify/vue"

import { defineProps } from 'vue'

import { useField } from 'vee-validate'

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

const props = defineProps<Props>()


const { value, errors } = useField(() => props.name, undefined)
</script>

<template>
  <div>
    <label
      class="input"
      :class="[ifValid && 'validator', props.class]"
      :style="{ width: width, height: height }"
    >
      <Icon v-if="icon" :icon="icon" :width="iconSize" :height="iconSize" />
      <input
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
    <p v-if="errors && errors.length" class="validator-hint">{{ errors }}</p>
  </div>
</template>

<style scoped></style>
