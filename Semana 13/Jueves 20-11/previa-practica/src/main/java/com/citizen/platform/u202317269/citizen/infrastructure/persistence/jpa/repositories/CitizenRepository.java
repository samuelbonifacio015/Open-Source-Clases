package com.citizen.platform.u202317269.citizen.infrastructure.persistence.jpa.repositories;

import com.citizen.platform.u202317269.citizen.domain.model.aggregates.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Citizen entities in the database.
 * @author Samuel Bonifacio
 */
@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
  /**
   * Check if a citizen exists by nickName.
   * @param nickName the nickName of the citizen
   * @return true if a citizen with the given nickName exists, false otherwise
   */
  boolean existsByNickName(String nickName);
  List<Citizen> findByNickName(String s);
}
