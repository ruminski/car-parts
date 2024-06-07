package wavestone.automotive.parts.domain.contributor.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wavestone.automotive.parts.domain.dealer.service.QueryService;
import wavestone.automotive.parts.model.entity.Part;

@RestController
@RequestMapping("/api/contributor/part")
@AllArgsConstructor
@Slf4j
public class PartContributorController {

    private QueryService service;

     /**
     * Changing the description of the part with the given id
     * @param id
     * @param partUpdate
     * @return
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Part updatePart(@PathVariable("id") final String id, @RequestBody final PartUpdate partUpdate) {
        return null;
    }

    /**
     * Adding a service action to the part with the given id,
     *
     * @param id Part ID
     * @return matching Part
     */
    @GetMapping("/{id}")
    public Part findById(@PathVariable long id) {
        return service.findPartById(id);
    }

}
