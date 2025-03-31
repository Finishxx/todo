package cz.orchitech.todobackend.api.dto;

import cz.orchitech.todobackend.model.TodoEntryStatus;
import jakarta.validation.constraints.NotNull;

/** Ideally we would love to use the TodoEntryEnum itself instead of "...|DOING|..." raw string */
public record TodoEntryRequestDto(
        String name,
        @NotNull
        TodoEntryStatus status) {
}
