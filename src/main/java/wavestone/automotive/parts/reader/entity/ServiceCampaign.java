package wavestone.automotive.parts.reader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class ServiceCampaign {

    @Id
    private CampaignName campaignName;

    private final LocalDate campaignStart;
    private final LocalDate campaignEnd;

}
