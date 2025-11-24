package com.citizen.platform.u202317269.citizen.domain.services;

import com.citizen.platform.u202317269.citizen.domain.model.aggregates.Citizen;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetAllCitizensQuery;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetCitizenByIdQuery;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetCitizenByNickNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service to handle citizen queries.
 * @author Samuel Bonifacio
 */
public interface CitizenQueryService {
  List<Citizen> handle(GetCitizenByNickNameQuery query);
  List<Citizen> handle(GetAllCitizensQuery query);
  List<Citizen> handle(GetCitizenByIdQuery query);
}
