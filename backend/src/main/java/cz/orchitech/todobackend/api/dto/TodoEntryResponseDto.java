package cz.orchitech.todobackend.api.dto;

public record TodoEntryResponseDto(
        Long id,
        String name,
        String status) {
}