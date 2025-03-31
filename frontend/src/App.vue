<script setup lang="ts">
import TodoApp from "@/components/TodoApp.vue"
import MainFooter from "@/components/MainFooter.vue"
import MainHeader from "@/components/MainHeader.vue"
import { ModalTarget } from "@kolirt/vue-modal"
</script>

<template>
  <!-- Needed for vue-modal to work -->
  <ModalTarget />

  <div class="app w3-blue-gray">
    <MainHeader />
    <TodoApp />
    <MainFooter />
  </div>
</template>

<style scoped>
.app {
  display: flex;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
  padding: 3rem;
  color: black;
}
</style>
