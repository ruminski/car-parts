package wavestone.automotive.parts.domain.dealer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wavestone.automotive.parts.domain.dealer.controller.PartFilter;
import wavestone.automotive.parts.exception.ResourceNotFoundException;
import wavestone.automotive.parts.model.dao.PartRepository;
import wavestone.automotive.parts.model.dao.ServiceCampaignRepository;
import wavestone.automotive.parts.model.entity.Part;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Service fetching from the DB. Read-only operations allowed.
 */
@Service
@AllArgsConstructor
@Slf4j
public class QueryService {

    private PartRepository partRepository;
    private ServiceCampaignRepository campaignRepository;

    public Collection<Part> findAllParts() {
        log.info("Find all parts");
        return partRepository.findAll();
    }

    public Collection<Part> findParts(String make, String model, PartFilter criteria) {
        log.info("Find parts with make: {}, model: {} and criteria: {}", make, model, criteria);
        if (criteria.partOfName().isBlank() && criteria.partOfDescription().isBlank()) {
            return partRepository.findByMakeAndModel(make, model);
        } else {
            return partRepository.findByMakeAndModelAndNameContainingAndDescriptionContaining(make, model, criteria.partOfName(), criteria.partOfDescription());
        }
    }

    public Part findPartById(Long id) {
        log.info("Find part with id: {}", id);
        return partRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to find part with id: " + id));
    }

    public Collection<ServiceCampaign> findCampaignsWithinDataRange(String make, String model, LocalDate fromDate, LocalDate toDate) {
        if (fromDate != null && toDate != null) {
            return campaignRepository.findByStartDateAfterAndEndDateBeforeAndPartsMakeAndPartsModel(fromDate, toDate, make, model);
        } else if (fromDate != null) {
            return campaignRepository.findByStartDateAfterAndPartsMakeAndPartsModel(fromDate, make, model);
        } else if (toDate != null) {
            return campaignRepository.findByEndDateBeforeAndPartsMakeAndPartsModel(toDate, make, model);
        } else {
            return campaignRepository.findByPartsMakeAndPartsModel(make, model);
        }
    }
}
