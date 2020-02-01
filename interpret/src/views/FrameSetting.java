package views;

import java.awt.*;

public final class FrameSetting {
    static final String FRAME_TITLE = "Interpret プログラム";

    static final Color BG_COLOR = Color.gray;

    static final int TEXT_FIELD_LENGTH = 10;

    static final String DEFAULT_RESULT = "ここに結果が表示されます";
    static final int RESULT_FIELD_ROWS = 5;
    static final int RESULT_FIELD_COLUMNS = 30;

    class Grid {
        static final int X_DEFAULT = 0;
        static final int Y_DEFAULT = 0;
        static final int WIDTH = 1;
        static final int HEIGHT = 1;
    }

    class ButtonLabel {
        static final String CREATE_OBJECT = "Create object";
    }

    class TextLabel {
        static final String TYPE = "型";
        static final String CONSTRUCTOR = "コンストラクタ";
        static final String PARAMETER = "パラメータ";
        static final String FIELD = "フィールド";
        static final String METHOD = "メソッド";
        static final String ARRAY_SIZE = "配列の長さ";
        static final String ARRAY_INDEX = "配列のインデックス";
    }
}
