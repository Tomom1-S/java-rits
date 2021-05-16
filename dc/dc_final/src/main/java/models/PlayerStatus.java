package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerStatus {
    PLAYING(1),
    PAUSE(2),
    STOPPED(4);

    private final int code;
}
