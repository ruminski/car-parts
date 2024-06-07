package wavestone.automotive.parts.domain.contributor.controller;

import jakarta.validation.constraints.NotNull;

public record PartUpdate(@NotNull String partDescription) {

}
