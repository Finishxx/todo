## Backend

Decided on a classic three-tiered architecture

Created the scaffolding for easily adding other entities to CRUD over.

It did not make sense to use the PUT, since I want ID generation to be on the
BE/DB fully.

Decided to start also provide an OpenAPI description of the API.

### TODOs

[ ] Docker - compose DB and the backend
[ ] Consider serving the frontend on some url
[ ] Maybe locking/transactional?
[ ] Unit test - ideally seeing the frontend and missing some feature would
inspire me to create some business logic actually worth testing

