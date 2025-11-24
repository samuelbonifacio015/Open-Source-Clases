package com.citizen.platform.u202317269.shared.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base class for all entities that require auditing capabilities.
 * @summary The class is a mapped superclass that adds auditing fields to the class.
 * @author Samuel Bonifacio
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableModel {
  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Getter
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Date createdDate;

  @Getter
  @Column(nullable = false)
  @LastModifiedDate
  private Date lastModifiedDate;
}
