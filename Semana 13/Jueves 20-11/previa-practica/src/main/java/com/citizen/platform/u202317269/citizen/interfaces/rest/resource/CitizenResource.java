package com.citizen.platform.u202317269.citizen.interfaces.rest.resource;

import java.util.Date;

/**
 * Citizen Resource
 * @author Samuel Bonifacio
 */
public record CitizenResource(Long citizenId, String name, String nickName,
                              Date birthDate, String profession) {}
