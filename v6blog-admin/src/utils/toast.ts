export type ToastType = 'info' | 'success' | 'warning' | 'error'

const icons: Record<ToastType, string> = {
  info: `
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="h-6 w-6 shrink-0 stroke-current">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
    </svg>
  `,
  success: `
    <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 shrink-0 stroke-current" fill="none" viewBox="0 0 24 24">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
    </svg>
  `,
  warning: `
    <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 shrink-0 stroke-current" fill="none" viewBox="0 0 24 24">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
    </svg>
  `,
  error: `
    <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 shrink-0 stroke-current" fill="none" viewBox="0 0 24 24">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
    </svg>
  `,
}

export function showToast(message: string, type: ToastType = 'info'): void {
  // 尝试查找容器，使用 class "toast"
  let container = document.querySelector('.toast')
  // 如果容器不存在，则创建一个
  if (!container) {
    container = document.createElement('div')
    // 设置容器的 class，不使用 id
    container.className = 'toast'
    // 添加到#app中去
    document.body.appendChild(container)
  }
  // 创建 alert 元素，使用 daisyUI alert 样式
  const alert = document.createElement('div')
  const className = `alert alert-${type} animate__animated animate__fast animate__fadeInRight`
  alert.className = className
  const icon = icons[type]
  alert.innerHTML = `
    ${icon}
    <span>${message}</span>
  `
  container.appendChild(alert)
  // 3秒后移除当前 toast，若容器中没有子节点则移除容器
  setTimeout(() => {
    const cs = container.children
    for (let i = 0; i < cs.length; i++) {
      if (cs[i] === alert) {
        const ele = cs[i]
        ele.className = className.replace('fadeInRight', 'fadeOutRight')
        ele.getAnimations().forEach((animation) => {
          animation.onfinish = () => {
            ele.remove()
          }
        })
      }
    }
  }, 3000)
}
