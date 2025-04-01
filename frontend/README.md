# TODO frontend

Rudimentary Typescript + Vue.js 3 frontend for Spring Boot backend made as a
part of interview preparation for a developer job.

## Get started

Run the dev server and open the site in your browser. The application requires a
backend server to be running to function properly.

```bash
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

## Known issues

- No thought was given to mobile users or responsiveness in general
- The application is single page, but not in the sense of SPA needing Vue
  Router.
- The styling and functionality are quite basic

## Attributions

The project was generated using
[create-vue](https://github.com/vuejs/create-vue).

Took **big** inspiration (part of the book used TODO app for project) from the
book
[Vue.js 3 Design Patterns and Best Practices](https://www.amazon.com/Vue-js-Design-Patterns-Best-Practices/dp/1803238070).

The used styling is [w3.css](https://www.w3schools.com/w3css/) and
[Font Awesome](https://fontawesome.com/).

Used libraries and packages are:
[vue-modal](https://github.com/kolirt/vue-modal),
[vue-loading-overlay](https://github.com/ankurk91/vue-loading-overlay),
[vue-toastification](https://github.com/Maronato/vue-toastification),
[axios](https://github.com/axios/axios)
