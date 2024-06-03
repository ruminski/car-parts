package wavestone.automotive.parts.reader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class ServiceCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private final String brand, model;
    private final LocalDate producedFrom, producedTo;
    private final String name;
    private final BigDecimal price;
    private final boolean isAvailable;
    private final int availableToShipInDays;


}
