import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

interface User {
  username: string
  nickname?: string
  realName?: string
  avatar?: string
  gender?: number
  age?: number
  email?: string
  lastLogin?: Date
}

export const useAuthStore = defineStore('auth', () => {
  const currentUser = ref<User | null>(null)

  const token = ref<string | null>(null)

  const login = (username: string, password: string) => {
    if (username === 'admin' && password === 'Aa123456..') {
      currentUser.value = {
        username,
        nickname: 'Admin',
        realName: 'Administrator',
        avatar: '',
        gender: 1,
        age: 18,
        email: 'admin@example.com',
        lastLogin: new Date(),
      }
      token.value = '123456789'
      localStorage.setItem('token', token.value)
      return Promise.resolve()
    } else {
      return Promise.reject()
    }
  }

  const logout = () => {
    currentUser.value = null
    localStorage.removeItem('token')
    token.value = ''
  }

  return { currentUser, token, login, logout }
})
