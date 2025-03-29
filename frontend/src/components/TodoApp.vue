<script setup lang="ts">
import { computed, type Ref, ref } from "vue"
import { type TodoEntry, TodoEntryStatus } from "@/model/TodoEntry.ts"

const newTodoText: Ref<string> = ref("")
const entries: Ref<TodoEntry[]> = ref([
  { id: 1, name: "Hello", status: TodoEntryStatus.TODO },
  { id: 2, name: "World", status: TodoEntryStatus.DONE },
])
const entriesTodo = computed(() => {
  return entries.value.filter(item => item.status == TodoEntryStatus.TODO)
})
const entriesDone = computed(() => {
  return entries.value.filter(item => item.status == TodoEntryStatus.DONE)
})

function clearTodo(): void {
  newTodoText.value = ""
}

function addTodo(): void {
  if (newTodoText.value !== "") {
    entries.value.push({
      id: new Date().valueOf(),
      name: newTodoText.value,
      status: TodoEntryStatus.TODO,
    })
    clearTodo()
  }
}
</script>

<template>
  <div class="todo-container w3-white w3-card-4">
    <!-- Header -->
    <div class="w3-container w3-black w3-margin-0 w3-bottombar w3-border-blue">
      <h1>
        <i class="fa-regular fa-clipboard-list"></i>
        TODO List
      </h1>
    </div>

    <!-- Input -->
    <div class="w3-container flex-container 3-light-gray w3-padding">
      <input
        class="w3-input w3-border-0"
        type="text"
        autofocus
        v-model="newTodoText"
        @keyup.enter="addTodo()"
        placeholder="Add TODO item..."
      />
      <button class="w3-button w3-gray" @click="clearTodo()">
        <i class="fa-solid fa-times"></i>
      </button>
      <button class="w3-button w3-blue" @click="addTodo()">
        <i class="fa-solid fa-plus"></i>
      </button>
    </div>

    <!-- List of TODO -->
    <div class="w3-padding w3-blue">TODO ({{ entriesTodo.length }}</div>
    <div class="w3-padding" v-for="todo in entriesTodo" :key="todo.id">
      <label>
        <input type="checkbox" v-model="todo.status" />
        <span class="w3-margin-left">
          {{ todo.name }}
        </span>
      </label>
    </div>
    <div class="w3-padding" v-show="entriesTodo.length == 0">No tasks</div>

    <!-- List of DONE -->
    <div class="w3-padding w3-blue">Done ({{ entriesDone.length }}</div>
    <div class="w3-padding" v-for="todo in entriesDone" :key="todo.id">
      <label>
        <input type="checkbox" v-model="todo.status" />
        <span class="w3-margin-left">
          {{ todo.name }}
        </span>
      </label>
    </div>
    <div class="w3-padding" v-show="entriesDone.length == 0">No tasks</div>
  </div>
</template>

<style scoped>
.todo-container {
  max-width: 100%;
  min-width: 30rem;
}

label {
  cursor: pointer;
  display: flex;
}
</style>
