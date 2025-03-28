package cz.orchitech.todobackend.api;

import cz.orchitech.todobackend.business.CrudService;
import cz.orchitech.todobackend.model.DomainEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.function.Function;
import java.util.stream.StreamSupport;

/**
 * Abstract base class for a simple Restful CRUD controller
 *
 * @param <E> Entity
 * @param <K> Key
 * @param <R> Response DTO (includes id)
 * @param <Q> Request DTO (no id)
 */
public abstract class CrudController<E extends DomainEntity<K>, K, R, Q> {

    protected CrudService<E, K> service;
    protected Function<E, R> toDtoConverter;
    protected Function<Q, E> toEntityConverter;

    public CrudController(CrudService<E, K> service, Function<E, R> toDtoConverter,
                          Function<Q, E> toEntityConverter) {
        this.service = service;
        this.toDtoConverter = toDtoConverter;
        this.toEntityConverter = toEntityConverter;
    }

    @GetMapping
    public ResponseEntity<Iterable<R>> readAll() {
        Iterable<R> result = StreamSupport
                .stream(service.readAll().spliterator(), false)
                .map(toDtoConverter).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<R> readOne(@PathVariable K id) {
        return service.readById(id)
                .map(entity -> ResponseEntity.ok(toDtoConverter.apply(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<R> create(@RequestBody Q dto) {
        E entity = toEntityConverter.apply(dto);
        entity.setId(null); // The id needs to be null to be generated automatically

        E created = service.create(entity);
        R response = toDtoConverter.apply(created);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<R> update(@RequestBody Q dto, @PathVariable K id) {
        E entity = toEntityConverter.apply(dto);
        entity.setId(id);
        try {
            E updated = service.update(entity);
            return ResponseEntity.ok(toDtoConverter.apply(updated));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable K id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
