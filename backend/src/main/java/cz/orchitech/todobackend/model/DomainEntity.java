package cz.orchitech.todobackend.model;

import java.io.Serializable;

public interface DomainEntity<ID> extends Serializable {

    ID getId();

    void setId(ID id);
}
