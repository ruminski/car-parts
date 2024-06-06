package wavestone.automotive.parts.domain.reader.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import wavestone.automotive.parts.TestCarPartsApplication;
import wavestone.automotive.parts.domain.reader.service.ReaderService;
import wavestone.automotive.parts.model.dao.ServiceCampaignRepository;
import wavestone.automotive.parts.model.entity.Part;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Import(TestCarPartsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReaderServiceTest {

    @Autowired
    ReaderService service;

    @Autowired
    ServiceCampaignRepository campaignRepository;

    @Transactional
    @Test
    @Order(1)
    void findAllParts() {
        Collection<Part> allParts = service.findAllParts();
        assertThat(allParts).hasSize(6); //see DBTestDataLoader
    }

    @Test
    @Order(2)
    void findPartsWithNullOrEmptyCriteria() {
        assertThat("to implement").isNotEmpty();
    }

    @Transactional
    @Test
    @Order(3)
    void findCampaignsWithinDataRange() {
        Collection<ServiceCampaign> noDateLimit = service.findCampaignsWithinDataRange("BMW", "320i", null, null);
        assertThat(noDateLimit).hasSize(2);

        Collection<ServiceCampaign> fromDateLimit = service.findCampaignsWithinDataRange("BMW", "320i", LocalDate.of(2016, 12, 01), null);
        assertThat(fromDateLimit).hasSize(1);

        Collection<ServiceCampaign> toDateLimit = service.findCampaignsWithinDataRange("BMW", "320i", null, LocalDate.of(2021, 12, 01));
        assertThat(toDateLimit).hasSize(1);

        Collection<ServiceCampaign> fromAndToDateLimit = service.findCampaignsWithinDataRange("BMW", "320i", LocalDate.of(2016, 12, 01), LocalDate.of(2019, 12, 01));
        assertThat(fromAndToDateLimit).isEmpty();
    }
}