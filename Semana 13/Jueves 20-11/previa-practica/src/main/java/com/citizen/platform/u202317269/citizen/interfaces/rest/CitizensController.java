package com.citizen.platform.u202317269.citizen.interfaces.rest;

import com.citizen.platform.u202317269.citizen.domain.model.queries.GetAllCitizensQuery;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetCitizenByIdQuery;
import com.citizen.platform.u202317269.citizen.domain.model.queries.GetCitizenByNickNameQuery;
import com.citizen.platform.u202317269.citizen.domain.services.CitizenCommandService;
import com.citizen.platform.u202317269.citizen.domain.services.CitizenQueryService;
import com.citizen.platform.u202317269.citizen.interfaces.rest.resource.CitizenResource;
import com.citizen.platform.u202317269.citizen.interfaces.rest.resource.CreateCitizenResource;
import com.citizen.platform.u202317269.citizen.interfaces.rest.transform.CitizenResourceFromEntityAssembler;
import com.citizen.platform.u202317269.citizen.interfaces.rest.transform.CreateCitizenCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * CitizensController
 * @summary CitizensController to handle citizen-related API requests
 * @author Samuel Bonifacio
 */
@RestController
@RequestMapping(value = "/api/v1/citizens", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Citizens", description = "Random User")
public class CitizensController {
  private final CitizenCommandService citizenCommandService;
  private final CitizenQueryService citizenQueryService;

  public CitizensController(CitizenCommandService citizenCommandService,
                            CitizenQueryService citizenQueryService) {
    this.citizenCommandService = citizenCommandService;
    this.citizenQueryService = citizenQueryService;
  }

  /**
   * POSTAPI Method to create a new citizen
   * @summary Create a new citizen with the provided details
   * @param resource CreateCitizenResource containing citizen details
   * @return
   */
  @PostMapping
  @Operation(summary = "Create a new citizen", description = "Create a new citizen with the provided details")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Citizen created successfully"),
      @ApiResponse(responseCode = "404", description = "Citizen could not be found")
  })
  public ResponseEntity<CitizenResource> createCitizen(@RequestBody CreateCitizenResource resource) {
    var createCitizenCommand = CreateCitizenCommandFromResourceAssembler.toCommandFromResource(resource);
    var citizenId = citizenCommandService.handle(createCitizenCommand);
    if (citizenId == null || citizenId == 0L) return ResponseEntity.badRequest().build();
    var citizens = citizenQueryService.handle(new GetCitizenByIdQuery(citizenId));
    if (citizens.isEmpty()) return ResponseEntity.notFound().build();
    var citizenResource = CitizenResourceFromEntityAssembler.toResourceFromEntity(citizens.get(0));
    return new ResponseEntity<>(citizenResource, HttpStatus.CREATED);
  }

  /**
   * GET API Method to get all citizens
   * @summary Get all citizens
   * @return
   */
  @GetMapping
  @Operation(summary = "Get all citizens", description = "Retrieve all citizens from the database")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Citizens retrieved successfully"),
      @ApiResponse(responseCode = "404", description = "No citizens found")
  })
  public ResponseEntity<List<CitizenResource>> getAllCitizens() {
    var citizens = citizenQueryService.handle(new GetAllCitizensQuery());
    if (citizens.isEmpty()) return ResponseEntity.notFound().build();
    var citizenResources = citizens.stream()
        .map(CitizenResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(citizenResources);
  }

  /**
   * GET API Method to get citizen by nickname
   * @summary Get citizen by nickname
   * @param nickName Nickname of the citizen
   * @return
   */
  @GetMapping("/nickname/{nickName}")
  @Operation(summary = "Get citizen by nickname", description = "Retrieve a citizen by their nickname")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Citizen found"),
      @ApiResponse(responseCode = "404", description = "Citizen not found")
  })
  public ResponseEntity<List<CitizenResource>> getCitizenByNickName(@PathVariable String nickName) {
    var citizens = citizenQueryService.handle(new GetCitizenByNickNameQuery(nickName));
    if (citizens.isEmpty()) return ResponseEntity.notFound().build();
    var citizenResources = citizens.stream()
        .map(CitizenResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(citizenResources);
  }
}
