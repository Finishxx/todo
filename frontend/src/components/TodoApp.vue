<script setup lang="ts">
import { computed, type Ref, ref } from "vue"
import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"
import TodoSummary from "@/components/todo/TodoSummary.vue"
import TodoFilter from "@/components/todo/TodoFilter.vue"
import TodoEntryList from "@/components/todo/TodoEntryList.vue"
import { openModal } from "@kolirt/vue-modal"
import TestModal from "@/components/modal/TestModal.vue"
import ConfirmDeleteModal from "@/components/modal/ConfirmDeleteModal.vue"
import EditTodoEntryModal, {
  type EditTodoEntryModalProps,
} from "@/components/modal/EditTodoEntryModal.vue"

const entries: Ref<TodoEntry[]> = ref([
  { id: 1, name: "Hello", status: TodoEntryStatus.TODO },
  { id: 2, name: "World", status: TodoEntryStatus.DONE },
  { id: 3, name: "World", status: TodoEntryStatus.DOING },
])
const filterText: Ref<string> = ref("")
const entriesTodo = computed(() => {
  return entries.value.filter(item => item.status == TodoEntryStatus.TODO)
})
const entriesDone = computed(() => {
  return entries.value.filter(item => item.status == TodoEntryStatus.DONE)
})

function addEntry() {
  openModal(TestModal, {
    test: "some props",
  })
    // runs when modal is closed via confirmModal
    .then(data => {
      console.log("success", data)
    })
    // runs when modal is closed via closeModal or esc
    .catch(() => {
      console.log("catch")
    })
}

function deleteEntry(entry: TodoEntry) {
  openModal(ConfirmDeleteModal, {
    areYouSureMessage: `Are you sure you want to delete entry "${entry.name}"`,
  })
    // runs when modal is closed via confirmModal
    .then(data => {
      console.log("success", data)
    })
    // runs when modal is closed via closeModal or esc
    .catch(() => {
      console.log("catch")
    })
}

function updateEntry(entry: TodoEntry) {
  openModal<EditTodoEntryModalProps, typeof EditTodoEntryModal>(
    EditTodoEntryModal,
    {
      name: entry.name,
      status: entry.status,
    },
  )
    .then(data => {
      console.log("success", data)
    })
    .catch(() => {
      console.log("catch")
    })
}

function changeStatus(entry: TodoEntry) {}
</script>

<template>
  <div class="todo-container w3-white w3-card-4">
    <TodoSummary :entries="entries" />

    <TodoFilter v-model="filterText" />

    <TodoEntryList
      :entries="entries"
      :filter="filterText"
      @delete="deleteEntry"
      @update="updateEntry"
      @changeStatus="changeStatus"
      @add="addEntry"
    ></TodoEntryList>
  </div>
</template>

<style scoped>
.todo-container {
  max-width: 100%;
  min-width: 30rem;
}
</style>
