package cz.orchitech.todobackend.business;

import cz.orchitech.todobackend.model.TodoEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoEntryService extends CrudService<TodoEntry, Long> {
    protected TodoEntryService(CrudRepository<TodoEntry, Long> repository) {
        super(repository);
    }
}
