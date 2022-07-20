package kh.farrukh.espielspringdatajpa.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This is a base class for all entities that have an id.
 */
@Getter
@Setter
// TODO: 7/20/22 read about mapped superclass and its properties
@MappedSuperclass
public abstract class EntityWithId {

    // TODO: 7/20/22 read about id and generation (with types)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}