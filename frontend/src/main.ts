import { createApp } from "vue"
import App from "./App.vue"
import { createModal } from "@kolirt/vue-modal"

const app = createApp(App)

app.use(
  createModal({
    transitionTime: 200,
    animationType: "slideDown",
    modalStyle: {
      padding: "5rem 2rem",
      align: "center",
      "z-index": 201,
    },
    overlayStyle: {
      "background-color": "rgba(0, 0, 0, .5)",
      "backdrop-filter": "blur(5px)",
      "z-index": 200,
    },
  }),
)

app.mount("#app")
