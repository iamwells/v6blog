<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps({
  class: {
    type: String,
  },
  width: {
    type: String,
  },
  height: {
    type: String,
  },
  type: {
    type: String,
    default: 'text',
  },
  value: {
    type: String,
  },
  placeholder: {
    type: String,
  },
  title: {
    type: String,
  },
  readonly: {
    type: Boolean,
  },
  minlength: {
    type: Number,
  },
  maxlength: {
    type: Number,
  },
  min: {
    type: Number,
  },
  max: {
    type: Number,
  },
  icon: {
    type: String,
  },
  iconSize: {
    type: String,
  },
  ifValid: {
    type: Boolean,
  },
  required: {
    type: Boolean,
  },
  pattern: {
    type: String,
  },
  validatorHint: {
    type: String,
  },
})

const iconName = computed(() => {
  return props.icon ? `icon-[${props.icon.replace(':', '--')}]` : props.icon
})
</script>

<template>
  <div>
    <label
      class="input"
      :class="[ifValid && 'validator', props.class]"
      :style="{ width: width, height: height }"
    >
      <span v-if="icon" :class="iconName" :style="{ width: iconSize, height: iconSize }"></span>
      <input
        :type="type"
        :value="value"
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
    <p v-if="ifValid && validatorHint" class="validator-hint">{{ validatorHint }}</p>
  </div>
</template>

<style scoped></style>
