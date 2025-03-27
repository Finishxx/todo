package cz.orchitech.todobackend.data;

import cz.orchitech.todobackend.model.TodoEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoEntryRepository extends JpaRepository<TodoEntry, Long> {
}
