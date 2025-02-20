<script setup lang="ts">
import { onMounted, ref } from 'vue'

const widthClass = ref('w-64')

function toggleMenu() {
  widthClass.value = widthClass.value === 'w-64' ? 'w-16' : 'w-64'
}

onMounted(() => {})
</script>

<template>
  <div id="admin-layout" class="min-h-screen flex flex-col box-border border-1">
    <header id="top-box" class="h-20 flex box-border border-1 border-blue-600">
      <div class="navbar bg-base-100 shadow-sm">
        <a class="btn btn-ghost text-xl">daisyUI</a>
      </div>
    </header>
    <div id="left-box" class="flex flex-1 box-border border-1 border-red-600">
      <aside
        id="menu-box"
        :class="widthClass"
        class="transition-all duration-500 flex box-border border-1 border-red-800"
      ></aside>
      <main id="main-box" class="flex-1 flex box-border border-1 border-yellow-400">
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
