<script setup lang="ts">
import TodoApp from "@/components/TodoApp.vue"
</script>

<template>
  <div class="app w3-blue-gray">
    <TodoApp />
  </div>
</template>

<style scoped>
.app {
  display: flex;
  justify-content: center;
  width: 100vw;
  min-height: 100vh;
  padding: 5rem;
}
</style>
