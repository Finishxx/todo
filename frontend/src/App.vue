<script setup lang="ts">
import TodoApp from "@/components/TodoApp.vue"
import MainFooter from "@/components/MainFooter.vue"
import MainHeader from "@/components/MainHeader.vue"
</script>

<template>
  <div class="app w3-blue-gray">
    <MainHeader />
    <TodoApp />
    <MainFooter />
  </div>
</template>

<style scoped>
.app {
  display: flex;
  justify-content: center;
  width: 100vw;
  min-height: 100vh;
  padding: 5rem;
  flex-direction: column;
}
</style>
