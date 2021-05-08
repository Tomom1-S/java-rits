package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacilityType {
    ATTRACTION_STANDBY("アトラクション", "realtime", "realtime-attr"),
    ATTRACTION_FASTPASS("ファストパス", "fastpass", "realtime-attr"),
    GREETING("グリーティング","greeting_realtime", "realtime-attr"),
    RESTAURANT("レストラン","restwait", "realtime-attr");

    private final String optionName;
    private final String queryValue;
    private final String htmlClass;

    public static FacilityType fromString(final String optionName) {
        for (FacilityType f : FacilityType.values()) {
            if (f.optionName.equalsIgnoreCase(optionName)) {
                return f;
            }
        }
        throw new IllegalArgumentException("No constant with optionName '" + optionName + "' found");
    }
}
