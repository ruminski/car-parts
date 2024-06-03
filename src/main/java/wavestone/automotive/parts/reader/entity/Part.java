package wavestone.automotive.parts.reader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private final String brand;
    private final String model;
    private final LocalDate producedFrom;
    private final LocalDate producedTo;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final List<ServiceCampaign> serviceCampaigns;
    private final boolean isAvailable;
    private final int availableToShipInDays;


}
