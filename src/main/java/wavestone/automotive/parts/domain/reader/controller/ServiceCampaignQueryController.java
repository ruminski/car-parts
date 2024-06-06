package wavestone.automotive.parts.domain.reader.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wavestone.automotive.parts.domain.reader.service.ReaderService;
import wavestone.automotive.parts.model.entity.Part;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/api/dealer/service/campaign")
@AllArgsConstructor
@Slf4j
public class ServiceCampaignQueryController {

    private ReaderService service;

    /**
     * Information about all service actions for a given vehicle (make/model) conducted in the given date range.
     * In addition to the above, the REST API should enable:
     *
     * @param make car brand e.g. Ford
     * @param model car model e.g. Focus
     * @param fromDate optional filter for Service Campaign Start Date
     * @param toDate optional filter for Service Campaign End Date
     * @return
     */
    @GetMapping(value = "/{make}/{model}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ServiceCampaign> findParts(@PathVariable String make, @PathVariable String model, @RequestParam(required = false) LocalDate fromDate, @RequestParam(required = false) LocalDate toDate) {
        return service.findCampaignsWithinDataRange(make, model, fromDate, toDate);
    }

    // CONTRIBUTOR BELOW

    /**
     * a. changing the description of the part with the given id,
     * b. adding a service action to the part with the given id,
     *
     * @param id
     * @param book
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Part updatePart(@PathVariable("id") final String id, @RequestBody final Part book) {
        return book;
    }
}
