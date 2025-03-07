<script setup lang="ts">
import type { MenuItem } from 'primevue/menuitem'
import { ref } from 'vue'
import { useRouter, type RouteRecordRaw } from 'vue-router'

// onMounted(() => {
//   const sideNav = document.getElementById('side-nav')
//   if (sideNav) {
//     console.log(sideNav)
//     new PerfectScrollbar(sideNav, {
//       suppressScrollX: true,
//     })
//   }
// })

const menus = ref<MenuItem[]>()

const router = useRouter()
const routes = router.getRoutes()

const adminRoute = routes.find((route) => {
  if (route.path === '/admin') {
    return true
  }
  return false
})

const getMenus = (routes: RouteRecordRaw[]): MenuItem[] => {
  const items: MenuItem[] = []
  routes.forEach((route: RouteRecordRaw) => {
    const routePlus = router.resolve({ name: route.name })
    const item: MenuItem = {
      label: routePlus?.meta?.menuName as string,
      icon: routePlus?.meta?.icon as string,
    }
    if (route?.children) {
      const childs = getMenus(route.children)
      item.items = childs
    } else {
      item.route = routePlus.fullPath
    }
    items.push(item)
  })
  return items
}

if (adminRoute?.children) {
  menus.value = getMenus(adminRoute.children)
}
</script>

<template>
  <div class="flex flex-col h-dvh gap-2">
    <header id="header" class="h-20 flex dark:text-white">
      <Toolbar class="p-4 w-full">
        <template #start>
          <div class="w-64 h-full flex justify-center items-center cursor-pointer select-none">
            <span class="text-2xl">v6blog Admin</span>
          </div>
        </template>

        <template #end>
          <div class="flex items-center justify-center gap-2">
            <SplitButton outlined severity="secondary">
              <OverlayBadge value="1" severity="danger" class="inline-flex">
                <Avatar label="A" size="normal" />
              </OverlayBadge>
              <span>Admin</span>
            </SplitButton>
          </div>
        </template>
      </Toolbar>
    </header>
    <main class="!flex flex-1 overflow-hidden gap-2">
      <nav
        id="side-nav"
        class="relative w-64 h-full p-2 border-[1px] border-gray-300 rounded-[6px]"
      >
        <ScrollPanel class="w-full h-full">
          <PanelMenu :model="menus">
            <template #item="{ item }">
              <RouterLink v-if="item.route" v-slot="{ href, navigate }" :to="item.route" custom>
                <a
                  v-ripple
                  class="flex items-center cursor-pointer text-surface-700 dark:text-surface-0 px-4 py-2"
                  :href="href"
                  @click="navigate"
                >
                  <span :class="item.icon" />
                  <span class="ml-2">{{ item.label }}</span>
                </a>
              </RouterLink>
              <a
                v-else
                v-ripple
                class="flex items-center cursor-pointer text-surface-700 dark:text-surface-0 px-4 py-2"
                :href="item.url"
                :target="item.target"
              >
                <span :class="item.icon" />
                <span class="ml-2">{{ item.label }}</span>
                <span v-if="item.items" class="pi pi-angle-down text-primary ml-auto" />
              </a>
            </template>
          </PanelMenu>
          <p v-for="i in 50" :key="i" class="text-center p-10">
            {{ i }}
          </p>
        </ScrollPanel>
      </nav>
      <section class="flex-1 p-4 border-[1px] rounded-[6px] border-gray-300">
        <RouterView />
      </section>
    </main>
  </div>
</template>

<style scoped>
div,
header,
nav,
main,
section {
  box-sizing: border-box;
  border-color: var(--p-content-border-color);
}
</style>
