<script setup lang="ts">
import { type Ref, ref } from "vue"
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

const entries: Ref<TodoEntry[]> = ref([
  { id: 1, name: "Hello", status: TodoEntryStatus.TODO },
  { id: 2, name: "World", status: TodoEntryStatus.DONE },
  { id: 3, name: "World", status: TodoEntryStatus.DOING },
])
const filterText: Ref<string> = ref("")
const toast = useToast()

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
      return TodoService.newTodo(data.name, data.status)
    })
    .then(newTodo => {
      entries.value.push(newTodo)
      toast("Add successful!", { type: TYPE.INFO })
    })
    .catch(error => {
      console.log(error)
      showNetworkErrorToast()
    })
}

function deleteEntry(entry: TodoEntry) {
  openModal(TodoConfirmDeleteModal, {
    areYouSureMessage: `Are you sure you want to delete entry "${entry.name}"`,
  })
    .then(() => {
      return TodoService.deleteTodo(entry.id)
    })
    .then(() => {
      const index = entries.value.findIndex(entry => entry.id == entry.id)
      // TODO: Delete from a list
      toast("Delete successful!", { type: TYPE.INFO })
    })
    .catch(error => {
      console.log(error)
      showNetworkErrorToast()
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
      return TodoService.patchTodo(updatedEntry)
    })
    .then(updated => {
      const index = entries.value.findIndex(entry => entry.id == updated.id)
      if (index == -1) {
        entries.value.push(updated)
      } else {
        entries.value[index] = updated
      }
      toast("Update successful!", { type: TYPE.INFO })
    })
    .catch(error => {
      console.log(error)
      showNetworkErrorToast()
    })
}

function changeStatus(entry: TodoEntry) {
  entry.status = TodoService.nextTodoState(entry.status)
}
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
  display: flex;
  flex-direction: column;
  height: 100vh;
}
</style>
