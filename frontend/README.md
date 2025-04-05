# TODO frontend

Typescript + Vue.js 3 frontend designed to interface with a Spring Boot backend.
Developed as a part of interview preparation for a developer job.

## Get started

To run the frontend (requires a running backend server to be running on
`http://localhost:8080` to function properly.):

```bash
$ npm install
$ npm run dev
...
  ➜  Local:   http://localhost:5173/
...
```

## Component hierarchy

- MainHeader
- Todo
    - TodoSummary: Counts how many tasks there are for each status
    - TodoFilter
    - TodoEntryList: Lists entries and hosts CRUD buttons
    - Modal
        - TodoConfirmDeleteModal
        - TodoEditModal
- MainFooter

The application follows a layered architecture, consisting of a presentation
layer (Vue components), a business logic layer (services), and a networking
layer (api classes) responsible for communicating with the backend.

## Current limitations

- Desktop-focused design with limited responsiveness in general
- The application is single page, but not in the sense of SPA needing Vue
  Router
- Minimalist styling and functionality focused on core features
- Hardwired address to backend (`http://localhost:8080`)

### Further improvements

- Additional features e.g. tags, folders, ...
- UI/UX refinements e.g. better sorting, moving items up/down, ...
- Server updates - polling/SSE/WebSocket
- Test coverage

## Attributions

Took **big** inspiration (part of the book used TODO app for project) from the
book
[Vue.js 3 Design Patterns and Best Practices](https://www.amazon.com/Vue-js-Design-Patterns-Best-Practices/dp/1803238070).

Used tools and libraries

- [create-vue](https://github.com/vuejs/create-vue).
- [w3.css](https://www.w3schools.com/w3css/) [Font Awesome](https://fontawesome.com/)
- [vue-modal](https://github.com/kolirt/vue-modal)
- [vue-loading-overlay](https://github.com/ankurk91/vue-loading-overlay)
- [vue-toastification](https://github.com/Maronato/vue-toastification)
- [axios](https://github.com/axios/axios)
