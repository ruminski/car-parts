package wavestone.automotive.parts.model.dao;

import org.springframework.data.repository.ListCrudRepository;
import wavestone.automotive.parts.model.entity.Part;

import java.util.List;

public interface PartRepository extends ListCrudRepository<Part, Long> {

    List<Part> findByMakeAndModel(String make, String model);

    List<Part> findByMakeAndModelAndNameContaining(String make, String model, String namePart);

    List<Part> findByMakeAndModelAndNameContainingAndDescriptionContaining(String make, String model, String namePart, String descriptionPart);
}
