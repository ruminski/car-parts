package wavestone.automotive.parts.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_campaigns",
        indexes = {
                @Index(name = "start_date", columnList = "start_date"),
                @Index(name = "end_date", columnList = "end_date")
        })
public class ServiceCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private CampaignName campaignName;

    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;

    @Nullable
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "parts_in_campaigns",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts = new HashSet<>();


    public Set<Part> setParts(Set<Part> parts) {
        this.parts.forEach(this::removePart);
        parts.forEach(this::addPart);
        return parts;
    }

    public void addPart(Part part) {
        this.parts.add(part);
        part.getServiceCampaigns().add(this);
    }

    public void removePart(final Part part) {
        if(part == null) return;
        Part existingPart = this.parts.stream().filter(p -> p.getId().equals(part.getId())).findFirst().orElse(null);
        if (existingPart != null) {
            this.parts.remove(existingPart);
            existingPart.getServiceCampaigns().remove(this);
        }
    }

    @SuppressWarnings("unused")
    public static class ServiceCampaignBuilder {
        private final Set<Part> parts = new HashSet<>();

        public ServiceCampaignBuilder parts(final Set<Part> parts) {
            throw new UnsupportedOperationException("Use ServiceCampaign.setParts instead");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceCampaign)) return false;
        return id != null && id.equals(((ServiceCampaign) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
