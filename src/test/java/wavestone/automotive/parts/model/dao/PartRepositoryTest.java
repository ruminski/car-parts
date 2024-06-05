package wavestone.automotive.parts.model.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import wavestone.automotive.parts.TestCarPartsApplication;
import wavestone.automotive.parts.model.entity.Part;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static wavestone.automotive.parts.data.DBTestDataLoader.STEERING_WHEEL;
import static wavestone.automotive.parts.data.DBTestDataLoader.STEERING_WHEEL_DESCRIPTION;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Import(TestCarPartsApplication.class)
class PartRepositoryTest {

    private static final BigDecimal price = BigDecimal.valueOf(500.00);
    private static final boolean isAvailable = true;
    private static final String make = "BMW";
    private static final String model = "318i";
    private static final int availableToShipInDays = 2;
    private static final LocalDate producedFrom = LocalDate.of(2012, Month.JANUARY, 1);
    private static final LocalDate producedTo = LocalDate.of(2018, Month.DECEMBER, 31);

    @Autowired
    private PartRepository partRepository;

    @Test
    @Order(1)
    void canStoreAllFields() {
        //given
        Part savedPart = partRepository.save(Part.builder()
                .make(make)
                .model(model)
                .name(STEERING_WHEEL)
                .description(STEERING_WHEEL_DESCRIPTION)
                .price(price)
                .isAvailable(isAvailable)
                .availableToShipInDays(availableToShipInDays)
                .producedFrom(producedFrom)
                .producedTo(producedTo)
                .build());

        //then
        assertThat(savedPart.getId()).isNotZero();
        checkPartFields(savedPart, make, model, price, isAvailable, availableToShipInDays, producedFrom, producedTo);

        Optional<Part> fetchedPartOpt = partRepository.findById(savedPart.getId());
        assertThat(fetchedPartOpt).isNotEmpty();
        checkPartFields(fetchedPartOpt.get(), make, model, price, isAvailable, availableToShipInDays, producedFrom, producedTo);
    }

    @Test
    @Order(2)
    void canFindByMakeAndModel() {
        //given
        Part savedPart = partRepository.save(Part.builder()
                .make(make)
                .model(model)
                .name(STEERING_WHEEL)
                .description(STEERING_WHEEL_DESCRIPTION)
                .producedFrom(producedFrom)
                .build());

        //then
        assertThat(savedPart.getId()).isNotZero();
        List<Part> byMakeAndModel = partRepository.findByMakeAndModel(make, model);
        assertThat(byMakeAndModel).hasSize(2);
    }

    @Test
    @Order(3)
    void canFindByMakeAndModelAndPartName() {
        //given previous tests successful
        //then
        List<Part> bySteering = partRepository.findByMakeAndModelAndNameContaining(make, model, "steer");
        assertThat(bySteering).hasSize(2);

        List<Part> byWheel = partRepository.findByMakeAndModelAndNameContaining(make, model, "whee");
        assertThat(byWheel).hasSize(2);

        List<Part> byWordsParts = partRepository.findByMakeAndModelAndNameContaining(make, model, "ering wh");
        assertThat(byWordsParts).hasSize(2);

        List<Part> byEmptyName = partRepository.findByMakeAndModelAndNameContaining(make, model, "");
        assertThat(byEmptyName).hasSize(2);
    }

    @Test
    @Order(4)
    void canFindByMakeAndModelAndPartNameAndDescription() {
        //given previous tests successful
        //then
        List<Part> byNullDescription = partRepository.findByMakeAndModelAndNameContainingAndDescriptionContaining(make, model, "steer", null);
        assertThat(byNullDescription).isEmpty();

        List<Part> byEmptyDescription = partRepository.findByMakeAndModelAndNameContainingAndDescriptionContaining(make, model, "whee", "");
        assertThat(byEmptyDescription).hasSize(2);

        List<Part> byWhiteCharOnlyInDescription = partRepository.findByMakeAndModelAndNameContainingAndDescriptionContaining(make, model, "ering wh", " ");
        assertThat(byWhiteCharOnlyInDescription).hasSize(2);

        List<Part> byLastWordInDescriptionAndEmptyName = partRepository.findByMakeAndModelAndNameContainingAndDescriptionContaining(make, model, "", " axel");
        assertThat(byLastWordInDescriptionAndEmptyName).hasSize(2);
    }


    private static void checkPartFields(Part part, String make, String model, BigDecimal price, boolean isAvailable, int availableToShipInDays, LocalDate producedFrom, LocalDate producedTo) {
        assertThat(part.getMake()).isEqualTo(make);
        assertThat(part.getModel()).isEqualTo(model);
        assertThat(part.getName()).isEqualTo(STEERING_WHEEL);
        assertThat(part.getDescription()).isEqualTo(STEERING_WHEEL_DESCRIPTION);
        assertThat(part.getPrice()).usingComparator(BigDecimal::compareTo).isEqualTo(price);
        assertThat(part.isAvailable()).isEqualTo(isAvailable);
        assertThat(part.getAvailableToShipInDays()).isEqualTo(availableToShipInDays);
        assertThat(part.getProducedFrom()).isEqualTo(producedFrom);
        assertThat(part.getProducedTo()).isEqualTo(producedTo);
    }


}