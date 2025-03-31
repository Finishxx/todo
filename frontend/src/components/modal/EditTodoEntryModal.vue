<script setup lang="ts">
import { closeModal, confirmModal, SimpleModal } from "@kolirt/vue-modal"
import { TodoEntryStatus } from "@/model/TodoEntry.ts"
import { type Ref, ref } from "vue"

/**
 * Used for both adding a new TodoEntry and for editing an already existing one.
 */

interface EditTodoEntryModalProps {
  name: string
  status: TodoEntryStatus
  title: string
}

/** For the moment is mimics the props, but that might change */
export type EditTodoEntryData = EditTodoEntryModalProps

const props = defineProps<EditTodoEntryModalProps>()

const name: Ref<string> = ref(props.name)
const status: Ref<TodoEntryStatus> = ref(props.status)
const statuses: TodoEntryStatus[] = Object.values(TodoEntryStatus)
</script>

<template>
  <SimpleModal class="w3-modal-content" :title="title" size="sm">
    <form class="w3-container">
      <input
        class="w3-input w3-border"
        type="text"
        v-model="name"
        placeholder="Todo name"
      /><br />
      <select class="w3-select" v-model="status">
        <option v-for="option in statuses" :key="option" :value="option">
          {{ option }}
        </option>
      </select>
    </form>

    <button
      @click="confirmModal({ name: name, status: status })"
      class="w3-button"
    >
      Confirm
    </button>
    <button @click="closeModal()" class="w3-button">Close</button>
  </SimpleModal>
</template>

<style scoped>
body {
  color: green;
}
</style>
