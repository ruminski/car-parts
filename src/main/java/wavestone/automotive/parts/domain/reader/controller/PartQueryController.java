package wavestone.automotive.parts.domain.reader.controller;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wavestone.automotive.parts.domain.reader.service.ReaderService;
import wavestone.automotive.parts.model.entity.Part;

import java.util.Collection;

@RestController
@RequestMapping("/api/dealer/part")
@AllArgsConstructor
@Slf4j
public class PartQueryController {

    private ReaderService service;

    /**
     * List of parts for a given car model and make, filtered by providing the name of the part
     * or the description of the part (also only some words occurring in these elements)
     *
     * @param make car brand e.g. Ford
     * @param model car model e.g. Focus
     * @param criteria optional filtering criteria for part name and part description
     * @return matching Parts
     */
    @PostMapping(value = "/{make}/{model}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Part> findParts(@PathVariable String make, @PathVariable String model, @RequestBody PartFilter criteria) {
        log.info("Find parts request");
        return service.findParts(make, model, criteria);
    }

    /**
     * Information on the availability and possible shipping date for parts with the given id.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Part findById(@PathVariable long id) {
        return service.findPartById(id);
    }

    // CONTRIBUTOR BELOW

    /**
     * Information about all service actions for a given vehicle (make/model) conducted in the given date range.
     * In addition to the above, the REST API should enable:
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
