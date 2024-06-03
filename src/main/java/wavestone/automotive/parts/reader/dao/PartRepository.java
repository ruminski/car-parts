package wavestone.automotive.parts.reader.dao;

import org.springframework.data.repository.ListCrudRepository;
import wavestone.automotive.parts.reader.entity.Part;

public interface PartRepository extends ListCrudRepository<Part, Long> {

}
