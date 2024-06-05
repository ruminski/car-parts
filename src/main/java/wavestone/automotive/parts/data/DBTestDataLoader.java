package wavestone.automotive.parts.data;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import wavestone.automotive.parts.model.dao.PartRepository;
import wavestone.automotive.parts.model.dao.ServiceCampaignRepository;
import wavestone.automotive.parts.model.entity.CampaignName;
import wavestone.automotive.parts.model.entity.Part;
import wavestone.automotive.parts.model.entity.ServiceCampaign;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

/**
 * Loads some example data on lower envs.
 */
@Profile(value = "default")
@AllArgsConstructor
@Slf4j
@Component
public class DBTestDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    public static final String STEERING_WHEEL = "steering wheel";
    public static final String STEERING_WHEEL_DESCRIPTION = "part of the steering system, located on the front axel";
    private final PartRepository partRepository;
    private final ServiceCampaignRepository serviceCampaignRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("App initiated. Load example records into DB.");
        Part part1 = partRepository.save(Part.builder()
                .make("BMW")
                .model("320i")
                .name(STEERING_WHEEL)
                .description(STEERING_WHEEL_DESCRIPTION)
                .price(BigDecimal.valueOf(500.00))
                .isAvailable(true)
                .availableToShipInDays(2)
                .producedFrom(LocalDate.of(2012, Month.JANUARY, 1))
                .producedTo(LocalDate.of(2018, Month.DECEMBER, 31))
                .build());

        Part part2 = partRepository.save(Part.builder()
                .make("BMW")
                .model("320d")
                .name(STEERING_WHEEL)
                .description(STEERING_WHEEL_DESCRIPTION)
                .price(BigDecimal.valueOf(500.00))
                .isAvailable(true)
                .availableToShipInDays(2)
                .producedFrom(LocalDate.of(2012, Month.JANUARY, 1))
                .producedTo(LocalDate.of(2018, Month.DECEMBER, 31))
                .build());

        Part part3 = partRepository.save(Part.builder()
                .make("BMW")
                .model("330i")
                .name(STEERING_WHEEL)
                .description(STEERING_WHEEL_DESCRIPTION)
                .price(BigDecimal.valueOf(550.00))
                .isAvailable(true)
                .availableToShipInDays(3)
                .producedFrom(LocalDate.of(2012, Month.JANUARY, 1))
                .producedTo(LocalDate.of(2018, Month.DECEMBER, 31))
                .build());

        Part part4 = partRepository.save(Part.builder()
                .make("BMW")
                .model("330d")
                .name(STEERING_WHEEL)
                .description(STEERING_WHEEL_DESCRIPTION)
                .price(BigDecimal.valueOf(550.00))
                .isAvailable(false)
                .producedFrom(LocalDate.of(2012, Month.JANUARY, 1))
                .producedTo(LocalDate.of(2018, Month.DECEMBER, 31))
                .build());

        serviceCampaignRepository.save(ServiceCampaign.builder()
                .campaignName(CampaignName.STEERING_SYSTEM_REPAIR)
                .campaignStart(LocalDate.of(2018, Month.DECEMBER, 31))
                .parts(Set.of(part1, part2, part3, part4))
                .build());

        Part part5 = partRepository.save(Part.builder()
                .make("BMW")
                .model("520d")
                .name("exhaust pipe")
                .description("part of the exhaust system, located in the rear axel")
                .price(BigDecimal.valueOf(250.00))
                .isAvailable(true)
                .availableToShipInDays(1)
                .producedFrom(LocalDate.of(2012, Month.JANUARY, 1))
                .producedTo(LocalDate.of(2018, Month.DECEMBER, 31))
                .build());

        Part part6 = partRepository.save(Part.builder()
                .make("BMW")
                .model("520i")
                .name("exhaust pipe")
                .description("part of the exhaust system, located in the rear axel")
                .price(BigDecimal.valueOf(200.00))
                .isAvailable(true)
                .availableToShipInDays(2)
                .producedFrom(LocalDate.of(2012, Month.JANUARY, 1))
                .producedTo(LocalDate.of(2018, Month.DECEMBER, 31))
                .build());

        serviceCampaignRepository.save(ServiceCampaign.builder()
                .campaignName(CampaignName.EXHAUST_SYSTEM_REPAIR)
                .campaignStart(LocalDate.of(2016, Month.JANUARY, 31))
                .parts(Set.of(part5, part6))
                .build());

        serviceCampaignRepository.save(ServiceCampaign.builder()
                .campaignName(CampaignName.GEARBOX_SOFTWARE_UPGRADE)
                .campaignStart(LocalDate.of(2014, Month.JULY, 31))
                .parts(Set.of())
                .build());

        serviceCampaignRepository.save(ServiceCampaign.builder()
                .campaignName(CampaignName.GENERAL_OVERVIEW_OF_PETROL_CARS)
                .campaignStart(LocalDate.of(2016, Month.MARCH, 31))
                .parts(Set.of(part1, part3, part6))
                .build());

        log.info("DB records successfully loaded.");
    }
}
