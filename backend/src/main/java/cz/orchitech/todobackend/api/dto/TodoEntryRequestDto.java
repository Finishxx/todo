package cz.orchitech.todobackend.api.dto;

import cz.orchitech.todobackend.model.TodoEntryStatus;
import jakarta.validation.constraints.NotNull;

public record TodoEntryRequestDto(
        String name,
        @NotNull
        TodoEntryStatus status) {
}
