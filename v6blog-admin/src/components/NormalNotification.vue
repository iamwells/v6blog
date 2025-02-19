<script setup lang="ts">
import { Notivue, NotivueSwipe, Notification, NotificationProgress } from 'notivue'
</script>

<template>
  <Notivue v-slot="item">
    <NotivueSwipe :item="item">
      <Notification :item="item">
        <NotificationProgress :item="item" />
      </Notification>
    </NotivueSwipe>
  </Notivue>
</template>

<style scoped></style>
