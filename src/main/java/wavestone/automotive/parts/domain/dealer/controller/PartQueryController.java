package wavestone.automotive.parts.domain.dealer.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wavestone.automotive.parts.domain.dealer.service.QueryService;
import wavestone.automotive.parts.model.entity.Part;

import java.util.Collection;

@RestController
@RequestMapping("/api/dealer/part")
@AllArgsConstructor
@Slf4j
public class PartQueryController {

    private QueryService service;

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
        return service.findParts(make, model, criteria);
    }

    /**
     * Information on the availability and possible shipping date for parts with the given id.
     *
     * @param id Part ID
     * @return matching Part
     */
    @GetMapping("/{id}")
    public Part findById(@PathVariable long id) {
        return service.findPartById(id);
    }

}
