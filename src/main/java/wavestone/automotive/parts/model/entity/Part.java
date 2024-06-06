package wavestone.automotive.parts.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String make;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String model;

    @NotNull
    @Column(nullable = false)
    private LocalDate producedFrom;

    @Nullable
    private LocalDate producedTo;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String name;

    @NotEmpty
    @Column(length = 500, nullable = false)
    private String description;

    @Nullable
    @Column(precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean isAvailable;

    private int availableToShipInDays;


    @ManyToMany(mappedBy = "parts")
    @JsonIgnore
    private Set<ServiceCampaign> serviceCampaigns = new HashSet<>();


    @SuppressWarnings("unused")
    public static class PartBuilder {
        private final Set<ServiceCampaign> serviceCampaigns = new HashSet<>();

        public PartBuilder serviceCampaigns(final Set<ServiceCampaign> serviceCampaigns) {
            throw new UnsupportedOperationException("Use ServiceCampaign.setParts instead");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;
        return id != null && id.equals(((Part) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
