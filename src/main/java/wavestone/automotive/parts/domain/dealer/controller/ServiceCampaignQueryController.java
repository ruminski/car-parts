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
import wavestone.automotive.parts.model.entity.ServiceCampaign;

import java.time.LocalDate;
import java.util.Collection;

@Tag(name = "Dealer API", description = "Endpoints dedicated for Dealers.")
@RestController
@RequestMapping("/api/dealer/service/campaign")
@AllArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class ServiceCampaignQueryController {

    private QueryService service;

    /**
     * Information about all Service Campaigns for a given vehicle (make/model) conducted in the given date range.
     *
     * @param make car brand e.g. Ford
     * @param model car model e.g. Focus
     * @param fromDate optional filter for Service Campaign Start Date
     * @param toDate optional filter for Service Campaign End Date
     * @return collection of Service Campaigns
     */
    @Operation(summary = "Information about all Service Campaigns for a given vehicle (make/model) conducted in the given date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service Campaigns",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = ServiceCampaign.class)))}),
            @ApiResponse(responseCode = "404", description = "Part not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping(value = "/{make}/{model}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ServiceCampaign> findParts(@Parameter(description = "car make e.g. BMW") @PathVariable String make,
                                                 @Parameter(description = "car model e.g. 320i") @PathVariable String model,
                                                 @Parameter(description = "date in ISO format e.g. 2020-12-01") @RequestParam(required = false) LocalDate fromDate,
                                                 @Parameter(description = "date in ISO format e.g. 2020-12-01") @RequestParam(required = false) LocalDate toDate) {
        return service.findCampaignsWithinDataRange(make, model, fromDate, toDate);
    }

}
