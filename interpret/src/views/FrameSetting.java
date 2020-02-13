package views;

import java.awt.*;

public final class FrameSetting {
    static final String FRAME_TITLE = "Interpret プログラム";

    static final Color BG_COLOR = Color.gray;

    static final int TEXT_FIELD_LENGTH = 20;
    static final int COMBO_BOX_WIDTH = 300;
    static final int COMBO_BOX_FONT_SIZE = 12;

    static final String DEFAULT_RESULT = "ここに結果が表示されます";
    static final int RESULT_FIELD_ROWS = 10;
    static final int RESULT_FIELD_COLUMNS = 35;

    class Grid {
        static final int X_DEFAULT = 0;
        static final int Y_DEFAULT = 0;
        static final int WIDTH = 1;
        static final int HEIGHT = 1;
    }

    class ButtonLabel {
        static final String CREATE_OBJECT = "Create object";
        static final String SEARCH_CLASS = "Search class";
        static final String CALL_CONSTRUCTOR = "Call constructor";
        static final String CHANGE_FIELD = "Change field";
        static final String CALL_METHOD = "Call method";
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

    class Message {
        static final String SUCCESS = "成功しました";
        static final String FAILURE = "失敗しました";
        static final String CREATE_OBJECT = "オブジェクトを生成します";
        static final String SEARCH_CLASS = "クラスを検索します";
        static final String CALL_CONSTRUCTOR = "コンストラクタを呼び出します";
        static final String GET_FIELD = "フィールドの値を取得します";
        static final String CHANGE_FIELD = "フィールドを変更します";
        static final String CALL_METHOD = "メソッドを呼び出します";
        static final String ERROR_INVALID_ARGS_NUMBER = "引数の数が合致しません";
    }
}
