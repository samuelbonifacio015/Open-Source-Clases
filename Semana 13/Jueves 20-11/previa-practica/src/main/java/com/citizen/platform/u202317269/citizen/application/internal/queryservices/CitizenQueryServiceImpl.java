package com.citizen.platform.u202317269.citizen.application.internal.queryservices;

import com.citizen.platform.u202317269.citizen.domain.model.aggregates.Citizen;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetAllCitizensQuery;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetCitizenByIdQuery;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetCitizenByNickNameQuery;
import com.citizen.platform.u202317269.citizen.domain.services.CitizenQueryService;
import com.citizen.platform.u202317269.citizen.infrastructure.persistence.jpa.repositories.CitizenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the CitizenQueryService interface for handling citizen-related queries.
 * @author Samuel Bonifacio
 */
@Service
public class CitizenQueryServiceImpl implements CitizenQueryService {
  private final CitizenRepository citizenRepository;

  public CitizenQueryServiceImpl(CitizenRepository citizenRepository) {
    this.citizenRepository = citizenRepository;
  }

  @Override
  public List<Citizen> handle(GetCitizenByNickNameQuery query) {
    return citizenRepository.findByNickName(query.nickName());
  }

  @Override
  public List<Citizen> handle(GetCitizenByIdQuery query) {
    return citizenRepository.findById(query.citizenId())
        .map(List::of)
        .orElse(List.of());
  }

  @Override
  public List<Citizen> handle(GetAllCitizensQuery query) {
    return citizenRepository.findAll();
  }
}
