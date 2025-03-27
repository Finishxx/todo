package cz.orchitech.todobackend.business;

import cz.orchitech.todobackend.model.DomainEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Provides basic CRUD operations over a repository.
 *
 * @param <E> Entity
 * @param <K> Key
 */
public abstract class CrudService<E extends DomainEntity<K>, K> {

    protected final CrudRepository<E, K> repository;

    protected CrudService(CrudRepository<E, K> repository) {
        this.repository = repository;
    }

    public E create(E entity) {
        if (entity.getId() != null && repository.existsById(entity.getId()))
            throw new EntityExistsException();
        return repository.save(entity);
    }

    public Optional<E> readById(K id) {
        return repository.findById(id);
    }

    public Iterable<E> readAll() {
        return repository.findAll();
    }

    public E update(E entity) {
        if (!repository.existsById(entity.getId()))
            throw new EntityNotFoundException();
        return repository.save(entity);
    }

    public void deleteById(K id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException();
        else
            repository.deleteById(id);
    }

    public void deleteAllyById(Collection<K> keys) {
        repository.deleteAllById(keys);
    }
}
