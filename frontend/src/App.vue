<script setup lang="ts">
import TodoApp from "@/components/TodoApp.vue"
import MainFooter from "@/components/MainFooter.vue"
import MainHeader from "@/components/MainHeader.vue"
import { ModalTarget } from "@kolirt/vue-modal"
</script>

<template>
  <ModalTarget />

  <div class="app w3-blue-gray">
    <MainHeader />
    <TodoApp />
    <MainFooter />
  </div>
</template>

<style scoped>
.app {
  width: 100vw;
  min-height: 100vh;
  padding: 3rem;
  flex-direction: column;
  color: black;
}
</style>
