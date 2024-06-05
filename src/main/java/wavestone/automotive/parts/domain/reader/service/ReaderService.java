package wavestone.automotive.parts.domain.reader.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wavestone.automotive.parts.domain.reader.controller.PartFilter;
import wavestone.automotive.parts.exception.PartNotFoundException;
import wavestone.automotive.parts.model.dao.PartRepository;
import wavestone.automotive.parts.model.entity.Part;

import java.util.Collection;

@Service
@AllArgsConstructor
@Slf4j
public class ReaderService {

    private PartRepository repository;

    public Collection<Part> findAllParts() {
        log.info("Find all parts");
        return repository.findAll();
    }

    public Collection<Part> findParts(String make, String model, PartFilter criteria) {
        log.info("Find parts with make: {}, model: {} and criteria: {}", make, model, criteria);
        if (criteria.partOfName().isBlank() && criteria.partOfDescription().isBlank()) {
            return repository.findByMakeAndModel(make, model);
        } else {
            return repository.findByMakeAndModelAndNameContainingAndDescriptionContaining(make, model, criteria.partOfName(), criteria.partOfDescription());
        }
    }

    public Part findPartById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PartNotFoundException("Failed to find part with id: " + id));
    }
}
