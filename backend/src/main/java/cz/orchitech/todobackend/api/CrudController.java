package cz.orchitech.todobackend.api;

import cz.orchitech.todobackend.business.CrudService;
import cz.orchitech.todobackend.model.DomainEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.StreamSupport;

/**
 * // TODO: Change
 * Abstract base class for CRUD controller
 *
 * @param <E> Entity
 * @param <K> Key
 * @param <R> Response DTO - includes id
 * @param <Q> Request DTO - no id
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

    @PostMapping
    public R create(@RequestBody Q dto) {
        try {
            E entity = toEntityConverter.apply(dto);
            return toDtoConverter.apply(service.create(entity));
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public Iterable<R> readAll() {
        return StreamSupport
                .stream(service.readAll().spliterator(), false)
                .map(toDtoConverter).toList();
    }

    @GetMapping("/{id}")
    public R readONe(@PathVariable K id) {
        return service.readById(id).map(toDtoConverter)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Q dto, @PathVariable K id) {
        try {
            E entity = toEntityConverter.apply(dto);
            entity.setId(id);
            service.update(entity);
        } catch (EntityNotFoundException | NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable K id) {
        try {
            service.deleteById(id);
        } catch (EntityNotFoundException | NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
