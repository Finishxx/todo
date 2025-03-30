<script setup lang="ts">
import { closeModal, confirmModal, SimpleModal } from "@kolirt/vue-modal"
import { TodoEntryStatus } from "@/model/TodoEntry.ts"
import { type Ref, ref } from "vue"

export interface EditTodoEntryModalProps {
  name: string
  status: TodoEntryStatus
}

const props = defineProps<EditTodoEntryModalProps>()

const name: Ref<string> = ref(props.name)
const status: Ref<TodoEntryStatus> = ref(props.status)
const statuses: TodoEntryStatus[] = Object.values(TodoEntryStatus)
</script>

<template>
  <SimpleModal class="w3-modal-content" title="Confirm delete" size="sm">
    <input class="w3-input" type="text" v-model="name" />
    <select class="w3-select" v-model="status">
      <option v-for="option in statuses" :value="option">
        {{ option }}
      </option>
    </select>
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
