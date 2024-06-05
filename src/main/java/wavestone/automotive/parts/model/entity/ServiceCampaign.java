package wavestone.automotive.parts.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_campaigns")
public class ServiceCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private CampaignName campaignName;

    @NotNull
    @Column(nullable = false)
    private LocalDate campaignStart;

    @Nullable
    private LocalDate campaignEnd;


    @ManyToMany
    @JoinTable(name = "parts_in_campaigns",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts;

}
