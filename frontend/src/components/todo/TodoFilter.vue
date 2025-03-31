<script setup lang="ts">
const model = defineModel()

function clear() {
  model.value = ""
}
</script>

<template>
  <div class="w3-container filter-container w3-padding">
    <input
      type="text"
      v-model="model"
      class="w3-input w3-border"
      placeholder="Filter..."
    />
    <button class="w3-button w3-border" @click="clear()">
      <i class="fas fa-times"></i>
    </button>
  </div>
</template>

<style scoped>
.filter-container {
  display: flex;
}
</style>
