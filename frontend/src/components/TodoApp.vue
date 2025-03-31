<script setup lang="ts">
import { computed, type ComputedRef, onMounted, type Ref, ref } from "vue"
import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"
import TodoSummary from "@/components/todo/TodoSummary.vue"
import TodoFilter from "@/components/todo/TodoFilter.vue"
import TodoEntryList from "@/components/todo/TodoEntryList.vue"
import { openModal } from "@kolirt/vue-modal"
import TodoConfirmDeleteModal from "@/components/todo/modal/TodoConfirmDeleteModal.vue"
import TodoEditModal, {
  type EditTodoEntryData,
} from "@/components/todo/modal/TodoEditModal.vue"
import { TodoService } from "@/service/TodoService.ts"
import { TYPE, useToast } from "vue-toastification"
import { type ActiveLoader, useLoading } from "vue-loading-overlay"

const entries: Ref<Map<number, TodoEntry>> = ref(new Map([]))

const entryList: ComputedRef<TodoEntry[]> = computed(() => {
  return Array.from(entries.value.values())
})

onMounted(async () => {
  try {
    loader = loading.show()
    const allEntries: TodoEntry[] = await TodoService.loadTodos()
    entries.value = new Map(allEntries.map(entry => [entry.id, entry]))
  } catch {
    showNetworkErrorToast()
  } finally {
    loader.hide()
  }
})

const filterText: Ref<string> = ref("")
const toast = useToast()
const loading = useLoading({})
let loader: ActiveLoader

function showNetworkErrorToast() {
  toast("Network error!", { type: TYPE.ERROR })
}

function addEntry() {
  openModal<EditTodoEntryData, typeof TodoEditModal>(TodoEditModal, {
    name: "",
    status: TodoEntryStatus.TODO,
    title: "Add TODO",
  })
    .then(data => {
      loader = loading.show()
      return TodoService.newTodo(data.name, data.status)
    })
    .then(newTodo => {
      entries.value.set(newTodo.id, newTodo)
      toast("Add successful!", { type: TYPE.INFO })
    })
    .catch(error => {
      console.log(error)
      showNetworkErrorToast()
    })
    .finally(() => {
      loader.hide()
    })
}

function deleteEntry(entry: TodoEntry) {
  openModal(TodoConfirmDeleteModal, {
    areYouSureMessage: `Are you sure you want to delete entry "${entry.name}"`,
  })
    .then(() => {
      loader = loading.show()
      return TodoService.deleteTodo(entry.id)
    })
    .then(() => {
      entries.value.delete(entry.id)
      toast("Delete successful!", { type: TYPE.INFO })
    })
    .catch(error => {
      console.log(error)
      showNetworkErrorToast()
    })
    .finally(() => {
      loader.hide()
    })
}

function updateEntry(entry: TodoEntry) {
  // Think that async/await would be less clear
  openModal<EditTodoEntryData, typeof TodoEditModal>(TodoEditModal, {
    name: entry.name,
    status: entry.status,
    title: "Update TODO",
  })
    .then(updateData => {
      const updatedEntry: TodoEntry = { id: entry.id, ...updateData }
      loader = loading.show()
      return TodoService.patchTodo(updatedEntry)
    })
    .then(updated => {
      // The ids should match
      if (updated.id != entry.id) throw Error()
      entries.value.set(updated.id, updated)
      toast("Update successful!", { type: TYPE.INFO })
    })
    .catch(error => {
      console.log(error)
      showNetworkErrorToast()
    })
    .finally(() => {
      loader.hide()
    })
}

function changeStatus(entry: TodoEntry) {
  entry.status = TodoService.nextTodoState(entry.status)
}
</script>

<template>
  <div class="todo-container w3-white w3-card-4">
    <TodoSummary :entries="entryList" />

    <TodoFilter v-model="filterText" />

    <TodoEntryList
      :entries="entryList"
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
  display: flex;
  flex-direction: column;
  height: 100vh;
}
</style>
