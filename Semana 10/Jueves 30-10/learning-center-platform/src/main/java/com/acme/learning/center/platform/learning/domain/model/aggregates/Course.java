package com.acme.learning.center.platform.learning.domain.model.aggregates;

import com.acme.learning.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;

@Entity
public class Course extends AuditableAbstractAggregateRoot<Course> {
}
