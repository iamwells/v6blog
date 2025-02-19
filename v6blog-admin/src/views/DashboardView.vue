<script setup lang="ts">
import { onMounted, ref } from 'vue'

const widthClass = ref('w-64')

function toggleMenu() {
  widthClass.value = widthClass.value === 'w-64' ? 'w-16' : 'w-64'
}

onMounted(() => {})
</script>

<template>
  <div class="min-h-screen flex flex-col box-border border-1">
    <header class="h-20 flex box-border border-1 border-blue-600">
      <div class="navbar bg-base-100 shadow-sm">
        <a class="btn btn-ghost text-xl">daisyUI</a>
      </div>
    </header>
    <div class="flex flex-1 box-border border-1 border-red-600">
      <aside
        :class="widthClass"
        class="transition-all duration-500 flex box-border border-1 border-red-800"
        id="menu-box"
      ></aside>
      <main class="flex-1 flex box-border border-1 border-yellow-400">
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
      </main>
    </div>
  </div>
</template>

<style scoped></style>
