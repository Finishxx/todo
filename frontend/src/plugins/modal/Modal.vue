<script setup lang="ts">
import { inject, computed, type ComputedRef } from "vue"
import { type ModalApi, modalApiKey } from "@/plugins/modal/index.ts"

interface Props {
  name: string
  title: string
}

const { name = "", title = "Modal dialog" } = defineProps<Props>()

const modalApi = inject<ModalApi>(modalApiKey) as ModalApi
const showModal: ComputedRef<boolean> = computed(() => {
  return modalApi.activeModalsName() === name
})
</script>

<template>
  <div class="w3-modal" v-if="showModal">
    <div class="w3-modal-content w3-card-4">
      <header>
        {{ title }}
      </header>
      <main>
        <slot></slot>
      </main>
      <footer class="w3-right-align w3-indigo">
        <button class="w3-button" @click="modalApi.accept()">Accept</button>
        <button class="w3-button" @click="modalApi.cancel()">Cancel</button>
      </footer>
    </div>
  </div>
</template>

<style scoped></style>
