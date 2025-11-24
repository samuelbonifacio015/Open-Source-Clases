package com.citizen.platform.u202317269.citizen.domain.model.aggregates;

import com.citizen.platform.u202317269.citizen.domain.model.commands.CreateCitizenCommand;
import com.citizen.platform.u202317269.citizen.domain.model.valueobjects.Country;
import com.citizen.platform.u202317269.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.Date;

/**
 * Represents a Citizen aggregate root in the domain model.
 * @summary The class extends the {@link AuditableAbstractAggregateRoot} class
 * and contains attributes related to a citizen.
 * @author Samuel Bonifacio
 */
@Entity
@Getter
public class Citizen extends AuditableAbstractAggregateRoot<Citizen> {
  private Long citizenId;
  private String name;
  private String nickName;
  private Date birthDate;
  private String profession;

  @Embedded
  public Country country;

  public Citizen() {
    this.citizenId = 0L;
    this.name = "";
    this.nickName = "";
    this.birthDate = new Date();
    this.profession = "";
  }


  public Citizen(CreateCitizenCommand command) {
    this.name = command.name();
    this.nickName = command.nickName();
    this.birthDate = command.birthDate();
    this.country = command.country();
    this.profession = command.profession();
  }

  public Citizen(Long citizenId, String name, String nickName, Date birthDate, String profession) {
    this.citizenId = citizenId;
    this.name = name;
    this.nickName = nickName;
    this.birthDate = birthDate;
    this.profession = profession;
  }
}
