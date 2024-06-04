package wavestone.automotive.parts.model.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CampaignNameConverter implements AttributeConverter<CampaignName, String> {
 
    @Override
    public String convertToDatabaseColumn(CampaignName campaignName) {
        if (campaignName == null) {
            return null;
        }
        return campaignName.getCode();
    }

    @Override
    public CampaignName convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(CampaignName.values())
          .filter(c -> c.getCode().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}