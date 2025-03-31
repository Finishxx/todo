package cz.orchitech.todobackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TodoEntry implements DomainEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private TodoEntryStatus status;

    public TodoEntry(Long id, String name, TodoEntryStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public TodoEntry() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TodoEntryStatus getStatus() {
        return status;
    }

    public void setStatus(TodoEntryStatus status) {
        this.status = status;
    }
}
