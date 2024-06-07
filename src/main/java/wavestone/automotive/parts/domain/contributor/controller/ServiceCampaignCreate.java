package wavestone.automotive.parts.domain.contributor.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import wavestone.automotive.parts.model.entity.CampaignName;

import java.time.LocalDate;

public record ServiceCampaignCreate(@NotNull CampaignName campaignName, @NotNull LocalDate startDate,
                                    @Nullable LocalDate endDate) {

}
