package com.acme.catchup.platform.news.interfaces;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.interfaces.resources.CreateFavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.resources.FavoriteSourceResource;
import com.acme.catchup.platform.news.interfaces.transform.CreateFavoriteSourceCommandFromResourceAssembler;
import com.acme.catchup.platform.news.interfaces.transform.FavoriteSourceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/favorite-sources", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Favorite Sources", description = "Endpoints for managing favorite news sources")
public class FavoriteSourcesController {
    private final FavoriteSourceCommandService favoriteSourceCommandService;
    private final FavoriteSourceQueryService favoriteSourceQueryService;

    public FavoriteSourcesController(FavoriteSourceCommandService favoriteSourceCommandService,
                                     FavoriteSourceQueryService favoriteSourceQueryService) {
        this.favoriteSourceCommandService = favoriteSourceCommandService;
        this.favoriteSourceQueryService = favoriteSourceQueryService;
    }

    @Operation(
            summary = "Create Favorite Source",
            description = "Creates a new favorite news source for a user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite source created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input data")
    })
    @PostMapping
    public ResponseEntity<FavoriteSourceResource> createFavoriteSource(@RequestBody CreateFavoriteSourceResource resource) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceCommandService.handle(
                CreateFavoriteSourceCommandFromResourceAssembler.toCommandFromResource(resource));
        return favoriteSource.map(source -> new ResponseEntity<>(
                FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @Operation(
            summary = "Get a Favorite Source by ID",
            description = "Gets a favorite source by provided ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite source found"),
            @ApiResponse(responseCode = "400", description = "Favorite source not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<FavoriteSourceResource> getFavoriteSourceById(@PathVariable Long id) {
        Optional<FavoriteSource> favoriteSource = favoriteSourceQueryService.handle(
                new GetFavoriteSourceByIdQuery(id));
        return favoriteSource.map(source -> ResponseEntity.ok(
                FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Get all favorite sources by news apikey
     * @param newsApikey News API key
     * @return ResponseEntity with list of FavoriteSourceResource or 404 if none found
     */
    private ResponseEntity<List<FavoriteSourceResource>> getAllFavoriteSourcesByNewsApikey(String newsApikey) {
        var getAllFavoriteSourcesByNewsApikeyQuery = new GetAllFavoriteSourcesByNewsApiKeyQuery(newsApikey);
        var favoriteSources = favoriteSourceQueryService.handle(getAllFavoriteSourcesByNewsApikeyQuery);
        if (favoriteSources.isEmpty()) { return ResponseEntity.notFound().build(); }
        var favoriteSourceResources = favoriteSources.stream()
                .map(FavoriteSourceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(favoriteSourceResources);
    }
    /**
     * Get favorite source by news apikey and source id
     * @param newsApikey News API key
     * @param sourceId Source ID
     * @return ResponseEntity with FavoriteSourceResource or 404 if not found
     */
    private ResponseEntity<FavoriteSourceResource> getFavoriteSourceByNewsApikeyAndSourceId(String newsApikey, String sourceId) {
        var getFavoriteSourceByNewsApikeyAndSourceIdQuery = new GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(newsApikey, sourceId);
        var favoriteSource = favoriteSourceQueryService.handle(getFavoriteSourceByNewsApikeyAndSourceIdQuery);
        if (favoriteSource.isEmpty()) { return ResponseEntity.notFound().build(); }
        return favoriteSource.map(source -> ResponseEntity.ok(
                FavoriteSourceResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get a Favorite Source by News Api Key and Source Id",
            description = "Gets a favorite source by provided News Api Key and Source Id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Favorite source(s) found"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Missing required parameters"),
            @ApiResponse(responseCode = "404", description = "Favorite source(s) not found"),
    })
    @Parameters({
            @Parameter(name = "newsApikey", description = "News API key of the user", required = true),
            @Parameter(name = "sourceId", description = "ID of the news source", required = false)
    })
    @GetMapping
    public ResponseEntity<?> getFavoriteSourcesWithParameters(
            @Parameter(name = "params", hidden = true)
            @RequestParam Map<String, String> params  ) {
        if (params.containsKey("newsApikey") && params.containsKey("sourceId")) {
            return getFavoriteSourceByNewsApikeyAndSourceId(
                    params.get("newsApikey"),
                    params.get("sourceId"));
        } else if (params.containsKey("newsApikey")) {
            return getAllFavoriteSourcesByNewsApikey(params.get("newsApikey"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
