package wavestone.automotive.parts.model.dao;

import org.springframework.data.repository.ListCrudRepository;
import wavestone.automotive.parts.model.entity.Part;

public interface PartRepository extends ListCrudRepository<Part, Long> {

}
