package wavestone.automotive.parts.model.dao;

import org.springframework.data.repository.ListCrudRepository;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

public interface ServiceCampaignRepository extends ListCrudRepository<ServiceCampaign, Long> {

}
