package wavestone.automotive.parts.domain.dealer.controller;

import jakarta.validation.constraints.NotNull;

public record PartFilter(@NotNull String partOfName, @NotNull String partOfDescription) {

    public PartFilter(final String partOfName, final String partOfDescription) {
        this.partOfName = partOfName == null ? "" : partOfName;
        this.partOfDescription = partOfDescription == null ? "" : partOfDescription;
    }
}
