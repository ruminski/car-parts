package wavestone.automotive.parts.model.dao;

import org.springframework.data.repository.ListCrudRepository;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

import java.time.LocalDate;
import java.util.List;

public interface ServiceCampaignRepository extends ListCrudRepository<ServiceCampaign, Long> {

    List<ServiceCampaign> findByPartsMakeAndPartsModel(String make, String model);

    List<ServiceCampaign> findByStartDateAfterAndPartsMakeAndPartsModel(LocalDate startDate, String make, String model);

    List<ServiceCampaign> findByEndDateBeforeAndPartsMakeAndPartsModel(LocalDate endDate, String make, String model);

    List<ServiceCampaign> findByStartDateAfterAndEndDateBeforeAndPartsMakeAndPartsModel(LocalDate startDate, LocalDate endDate, String make, String model);

}
