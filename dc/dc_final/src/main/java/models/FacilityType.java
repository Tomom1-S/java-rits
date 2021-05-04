package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FacilityType {
    ATTRACTION_STANDBY("realtime", "realtime-attr"),
    ATTRACTION_FASTPASS("fastpass", "realtime-hoge"),
    GREETING("greeting_realtime", "realtime-hoge"),
    RESTAURANT("restwait", "realtime-hoge");

    private final String value;
    private final String htmlClass;
}
