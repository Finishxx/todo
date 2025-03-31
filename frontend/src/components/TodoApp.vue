<script setup lang="ts">
import { computed, type ComputedRef, onMounted, type Ref, ref } from "vue"
import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"
import TodoSummary from "@/components/todo/TodoSummary.vue"
import TodoFilter from "@/components/todo/TodoFilter.vue"
import TodoEntryList from "@/components/todo/TodoEntryList.vue"
import { openModal } from "@kolirt/vue-modal"
import TodoEditModal, {
  type EditTodoEntryData,
} from "@/components/todo/modal/TodoEditModal.vue"
import { TodoService } from "@/service/TodoService.ts"
import { TYPE, useToast } from "vue-toastification"
import { type ActiveLoader, useLoading } from "vue-loading-overlay"
import TodoConfirmDeleteModal from "@/components/todo/modal/TodoConfirmDeleteModal.vue"

const entries: Ref<Map<number, TodoEntry>> = ref(new Map([]))

const entryList: ComputedRef<TodoEntry[]> = computed(() => {
  return Array.from(entries.value.values())
})

onMounted(async () => {
  try {
    startLoading()
    const allEntries: TodoEntry[] = await TodoService.loadTodos()
    entries.value = new Map(allEntries.map(entry => [entry.id, entry]))
  } catch {
    showNetworkErrorToast()
  } finally {
    stopLoading()
  }
})

const filterText: Ref<string> = ref("")
const toast = useToast()
const loading = useLoading({
  opacity: 0,
})
let loader: ActiveLoader

function startLoading() {
  loader = loading.show()
}

function stopLoading() {
  loader.hide()
}

function showNetworkErrorToast() {
  toast("Network error!", { type: TYPE.ERROR })
}

async function addEntry() {
  let modalData
  try {
    modalData = await openModal<EditTodoEntryData, typeof TodoEditModal>(
      TodoEditModal,
      {
        name: "",
        status: TodoEntryStatus.TODO,
        title: "Add TODO",
      },
    )
  } catch {
    return
  }
  startLoading()
  try {
    const newTodo = await TodoService.newTodo(modalData.name, modalData.status)
    entries.value.set(newTodo.id, newTodo)
    toast("Add successful!", { type: TYPE.INFO })
  } catch {
    showNetworkErrorToast()
  } finally {
    stopLoading()
  }
}

async function deleteEntry(entry: TodoEntry) {
  try {
    await openModal(TodoConfirmDeleteModal, {
      areYouSureMessage: `Are you sure you want to delete entry "${entry.name}"`,
    })
  } catch {
    return
  }
  startLoading()
  try {
    await TodoService.deleteTodo(entry.id)
    entries.value.delete(entry.id)
    toast("Delete successful!", { type: TYPE.INFO })
  } catch {
    showNetworkErrorToast()
  } finally {
    stopLoading()
  }
}

async function updateEntry(entry: TodoEntry) {
  let modalData
  try {
    modalData = await openModal<EditTodoEntryData, typeof TodoEditModal>(
      TodoEditModal,
      {
        name: entry.name,
        status: entry.status,
        title: "Update TODO",
      },
    )
  } catch {
    return
  }
  startLoading()
  try {
    const updatedEntry: TodoEntry = { id: entry.id, ...modalData }
    const patchedEntry = await TodoService.patchTodo(updatedEntry)
    if (patchedEntry.id != entry.id) throw Error()
    entries.value.set(patchedEntry.id, patchedEntry)
    toast("Update successful!", { type: TYPE.INFO })
  } catch {
    showNetworkErrorToast()
  } finally {
    stopLoading()
  }
}

async function changeStatus(entry: TodoEntry) {
  loader = loading.show()
  const updatedEntry: TodoEntry = { ...entry }
  updatedEntry.status = TodoService.nextTodoState(entry.status)
  try {
    await TodoService.patchTodo(updatedEntry)
    entry.status = updatedEntry.status
  } catch {
    showNetworkErrorToast()
  } finally {
    loader.hide()
  }
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
