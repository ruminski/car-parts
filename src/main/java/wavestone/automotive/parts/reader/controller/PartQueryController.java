package wavestone.automotive.parts.reader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wavestone.automotive.parts.exception.PartNotFoundException;
import wavestone.automotive.parts.reader.dao.PartRepository;
import wavestone.automotive.parts.reader.entity.Part;

import java.util.Collection;

@RestController
@RequestMapping("/api/dealer/part")
public class PartQueryController {

    @Autowired
    private PartRepository repository;

    @GetMapping("/{id}")
    public Part findById(@PathVariable long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PartNotFoundException());
    }

    @GetMapping("/")
    public Collection<Part> findBooks() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Part updateBook(
            @PathVariable("id") final String id, @RequestBody final Part book) {
        return book;
    }
}
