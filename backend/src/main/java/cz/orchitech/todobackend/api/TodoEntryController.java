package cz.orchitech.todobackend.api;

import cz.orchitech.todobackend.api.dto.TodoEntryDto;
import cz.orchitech.todobackend.api.dto.TodoEntryRequestDto;
import cz.orchitech.todobackend.business.TodoEntryService;
import cz.orchitech.todobackend.model.TodoEntry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entries")
public class TodoEntryController extends CrudController<TodoEntry, Long, TodoEntryDto, TodoEntryRequestDto> {

    public TodoEntryController(
            TodoEntryService service) {
        // Converters inline for simplicity’s sake
        super(
                service,
                entity -> new TodoEntryDto(entity.getId(), entity.getName()),
                dto -> new TodoEntry(null, dto.name()));
    }


}
