package wavestone.automotive.parts.domain.dealer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wavestone.automotive.parts.domain.dealer.service.QueryService;
import wavestone.automotive.parts.exception.ErrorResponse;
import wavestone.automotive.parts.model.entity.Part;

import java.util.Collection;

@Tag(name = "Dealer API", description = "Endpoints dedicated for Dealers.")
@RestController
@RequestMapping("/api/dealer/part")
@AllArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class PartQueryController {

    private QueryService service;

    /**
     * Collection of parts for a given car model and make, filtered by providing the name of the part
     * or the description of the part (also only some words occurring in these elements)
     *
     * @param make     car brand e.g. Ford
     * @param model    car model e.g. Focus
     * @param criteria optional filtering criteria for part name and part description
     * @return matching Parts
     */
    @Operation(summary = "Collection of parts for a given car model and make, filtered by providing the name of the part" +
            " or the description of the part (also only some words occurring in these elements)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Collection of Parts",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = Part.class)))}),
    })
    @PostMapping(value = "/{make}/{model}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Part> findParts(@Parameter(description = "car make e.g. BMW") @PathVariable String make,
                                      @Parameter(description = "car model e.g. 320i") @PathVariable String model,
                                      @RequestBody PartFilter criteria) {
        return service.findParts(make, model, criteria);
    }

    /**
     * Information on the availability and possible shipping date for Parts with the given id.
     *
     * @param id Part ID
     * @return matching Part
     */
    @Operation(summary = "Information on the availability and possible shipping date for Parts with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Part data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Part.class))}),
            @ApiResponse(responseCode = "404", description = "Part not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/{id}")
    public Part findById(@Parameter(description = "ID e.g. 1") @PathVariable Long id) {
        return service.findPartById(id);
    }

}
