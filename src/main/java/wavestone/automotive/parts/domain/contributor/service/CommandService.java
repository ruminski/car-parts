package wavestone.automotive.parts.domain.contributor.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wavestone.automotive.parts.exception.ResourceNotFoundException;
import wavestone.automotive.parts.model.dao.PartRepository;
import wavestone.automotive.parts.model.dao.ServiceCampaignRepository;
import wavestone.automotive.parts.model.entity.Part;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

import java.util.Optional;

/**
 * Service mutating DB
 */
@Service
@AllArgsConstructor
@Slf4j
public class CommandService {

    private PartRepository partRepository;
    private ServiceCampaignRepository campaignRepository;

    /**
     *
     * @param partId
     * @param partDescription
     * @return
     */
    @Transactional
    public Part updatePartDescription(Long partId, String partDescription) {
        log.info("Update part description for partId: {}", partId);
        Optional<Part> byId = partRepository.findById(partId);
        if (byId.isEmpty()) {
            throw new ResourceNotFoundException("Failed to fetch part with id: " + partId);
        }
        Part part = byId.get();
        part.setDescription(partDescription);
        return part;
    }

    /**
     *
     * @param partId
     * @param serviceCampaign
     * @return
     */
    @Transactional
    public Part addServiceCampaignToPart(Long partId, ServiceCampaign serviceCampaign) {
        log.info("Add Service Campaign to Part with id: {}", partId);
        Optional<Part> byId = partRepository.findById(partId);
        if (byId.isEmpty()) {
            throw new ResourceNotFoundException("Failed to fetch part with id: " + partId);
        }
        Part part = byId.get();
        serviceCampaign.addPart(part);
        campaignRepository.save(serviceCampaign);
        return part;
    }
}
