<script setup lang="ts">
import { closeModal, confirmModal, SimpleModal } from "@kolirt/vue-modal"

interface Props {
  areYouSureMessage: string
}

const { areYouSureMessage } = defineProps<Props>()
</script>

<template>
  <SimpleModal class="w3-modal-content" title="Confirm delete" size="sm">
    <p class="w3-large">{{ areYouSureMessage }}</p>
    <button @click="confirmModal()" class="w3-button">Confirm</button>
    <button @click="closeModal()" class="w3-button">Close</button>
  </SimpleModal>
</template>

<style scoped>
body {
  color: green;
}
</style>
