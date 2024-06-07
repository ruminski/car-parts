package wavestone.automotive.parts.domain.contributor.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import wavestone.automotive.parts.TestCarPartsApplication;
import wavestone.automotive.parts.model.dao.PartRepository;
import wavestone.automotive.parts.model.dao.ServiceCampaignRepository;
import wavestone.automotive.parts.model.entity.CampaignName;
import wavestone.automotive.parts.model.entity.Part;
import wavestone.automotive.parts.model.entity.ServiceCampaign;


import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Import(TestCarPartsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommandServiceTest {

    @Autowired
    CommandService service;

    @Autowired
    ServiceCampaignRepository campaignRepository;

    @Autowired
    PartRepository partRepository;

    @Test
    @Order(1)
    void updatePartDescription() {
        Throwable ex = catchThrowable(() -> service.updatePartDescription(1L, null));
        assertThat(ex).hasMessageContaining("Description must not be null or empty");

        ex = catchThrowable(() -> service.updatePartDescription(1L, ""));
        assertThat(ex).hasMessageContaining("Description must not be null or empty");

        ex = catchThrowable(() -> service.updatePartDescription(null, "desc"));
        assertThat(ex).hasMessageContaining("The given id must not be null");

        Part part = service.updatePartDescription(1L, "new desc");
        assertThat(part.getDescription()).isEqualTo("new desc");
    }

    @Transactional
    @Test
    @Order(2)
    void addServiceCampaignToPart() {
        //given
        Part existingPart = partRepository.findById(2L).get();
        assertThat(existingPart.getServiceCampaigns()).hasSize(1);
        assertThat(campaignRepository.findAll().stream()).hasSize(4);

        //when
        ServiceCampaign campaign = ServiceCampaign.builder()
                .campaignName(CampaignName.GENERAL_OVERVIEW_OF_PETROL_CARS)
                .startDate(LocalDate.of(2024, Month.JULY, 31))
                .build();
        Part part = service.addServiceCampaignToPart(2L, campaign);
        //then
        assertThat(part.getId()).isNotNull();
        assertThat(part.getServiceCampaigns()).hasSize(2);
        assertThat(campaignRepository.findAll().stream()).hasSize(5);
        Part updatedPart = partRepository.findById(2L).get();
        assertThat(updatedPart.getServiceCampaigns()).hasSize(2);
        assertThat(updatedPart.getServiceCampaigns()).contains(campaign);
    }
}