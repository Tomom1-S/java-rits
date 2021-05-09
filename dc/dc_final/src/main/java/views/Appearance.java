package views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Appearance {
    private double locX;
    private double locY;
    private String font;
    private int fontSize;
    private String fontColor;
    private String bgColor;
}
