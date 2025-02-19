<script setup lang="ts">
import { RouterView } from 'vue-router'
import NormalNotification from './components/NormalNotification.vue'
</script>

<template>
  <NormalNotification />
  <RouterView v-slot="{ Component }">
    <template v-if="Component">
      <Transition
        enter-active-class="animate__animated animate__zoomIn"
        leave-active-class="animate__animated animate__zoomOut"
      >
        <KeepAlive>
          <Suspense>
            <component :is="Component" />
          </Suspense>
        </KeepAlive>
      </Transition>
    </template>
  </RouterView>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
