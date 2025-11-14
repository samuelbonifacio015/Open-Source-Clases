package com.acme.learning.center.platform.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base class for all aggregate roots that require auditing capabilities.
 * @param <T> The type of the aggregate root extending this class.
 * @summary The class is an abstract class that extends the {@link AbstractAggregateRoot} class
 * and adds auditing fields to the class.
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>>
extends AbstractAggregateRoot<T>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    private Date lastModifiedDate;

    /**
     * Registers a domain event to the aggregate root.
     * @param event The domain event to be registered.
     */
    public void addDomainEvent(Object event) {
        super.registerEvent(event);
    }
}
