package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacilityType {
    ATTRACTION_STANDBY("realtime", "realtime-attr"),
    ATTRACTION_FASTPASS("fastpass", "realtime-attr"),
    GREETING("greeting_realtime", "realtime-attr"),
    RESTAURANT("restwait", "realtime-attr");

    private final String value;
    private final String htmlClass;
}
