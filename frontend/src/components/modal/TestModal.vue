<script setup lang="ts">
import { closeModal, confirmModal, SimpleModal } from "@kolirt/vue-modal"

const props = defineProps({
  test: {},
})
</script>

<template>
  <SimpleModal class="w3-modal-content" title="Test modal" size="sm">
    <p>props: {{ props }}</p>

    <button @click="confirmModal({ value: 'some values' })" class="b3-button">
      Confirm
    </button>
    <button @click="closeModal()" class="btn btn-primary">Close</button>
  </SimpleModal>
</template>

<style scoped>
body {
  color: green;
}
</style>
