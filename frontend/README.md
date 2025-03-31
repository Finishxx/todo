# TODO frontend

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

Using modals might not lead to best user experience. No thought was given to mobile users. The application is single
page, but not in the sense of SPA needing Vue Router.

## Attributions

The project was generated using
[create-vue](https://github.com/vuejs/create-vue).

Took **big** inspiration (part of the book used TODO app as a project) from
the book
[Vue.js 3 Design Patterns and Best Practices](https://www.amazon.com/Vue-js-Design-Patterns-Best-Practices/dp/1803238070).

The used styling is [w3.css](https://www.w3schools.com/w3css/) and
[Font Awesome](https://fontawesome.com/).
