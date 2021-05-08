package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Park {
    TDL("東京ディズニーランド", "land"),
    TDS("東京ディズニーシー", "sea"),
    USJ("ユニバーサル・スタジオ・ジャパン", "usj");

    private final String optionName;
    private final String queryValue;

    public static Park fromString(final String optionName) {
        for (Park p : Park.values()) {
            if (p.optionName.equalsIgnoreCase(optionName)) {
                return p;
            }
        }
        throw new IllegalArgumentException("No constant with optionName '" + optionName + "' found");
    }
}
