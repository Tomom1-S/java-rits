package ch24.ex01;

import java.util.ListResourceBundle;

public class GlobalRes_en_AU extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
            {GlobalRes.GOODBYE, "G'day"},
    };
}
