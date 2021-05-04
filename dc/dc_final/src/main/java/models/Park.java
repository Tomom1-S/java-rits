package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Park {
    TDL("land"),
    TDS("sea"),
    USJ("usj");

    private final String value;
}
