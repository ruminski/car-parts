package wavestone.automotive.parts.reader.entity;

public enum CampaignName {
    STEERING_SYSTEM_REPAIR("SS_R_24_1"), EXHAUST_SYSTEM_REPAIR("ES_R_24_1"), GEARBOX_SOFTWARE_UPGRADE("GB_S_U_24_1");

    private final String code;

    CampaignName(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

