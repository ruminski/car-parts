package wavestone.automotive.parts.domain.contributor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wavestone.automotive.parts.domain.contributor.service.CommandService;
import wavestone.automotive.parts.exception.ErrorResponse;
import wavestone.automotive.parts.model.entity.Part;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

@Tag(name = "Contributor API", description = "Endpoints that allow data modifications")
@RestController
@RequestMapping("/api/contributor/part")
@AllArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class PartContributorController {

    private CommandService service;

    /**
     * Changing the description of the part with the given id
     *
     * @param id         Part ID
     * @param partUpdate object containing Part fields to be updated
     * @return updated Part
     */
    @Operation(summary = "Changing the description of the part with the given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Part Description",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Part.class))}),
            @ApiResponse(responseCode = "404", description = "Part not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Part updatePart(@PathVariable("id") final Long id, @RequestBody final PartUpdate partUpdate) {
        Part part = service.updatePartDescription(id, partUpdate.partDescription());
        log.info("Set part description for partId: {}, description: {}", part.getId(), part.getDescription());
        return part;
    }

    /**
     * Adding a new Service Campaign to the part with the given id
     *
     * @param id              Part ID
     * @param serviceCampaign new Service Campaign to be added to the part
     * @return updated Part
     */
    @Operation(summary = "Adding a new Service Campaign to the part with the given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Part Description",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Part.class))}),
            @ApiResponse(responseCode = "404", description = "Part not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping("/{id}/service/campaign")
    public Part addServiceCampaign(@PathVariable final Long id, @RequestBody final ServiceCampaign serviceCampaign) {
        Part part = service.addServiceCampaignToPart(id, serviceCampaign);
        log.info("Create Service Campaign: {}, and add Part with id: {}", part.getId(), serviceCampaign);
        return part;
    }

}
