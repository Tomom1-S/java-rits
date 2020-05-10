package views;

import java.awt.*;

public final class FrameSetting {
    static final String FRAME_TITLE = "Interpret プログラム";
    static final String FRAME_PARAMS = "Parameters";
    static final String FRAME_FIELD = "Field";


    static final Color BG_COLOR = Color.gray;
    static final Color PARAMS_BTN_COLOR = Color.blue;

    static final int TEXT_FIELD_LENGTH = 20;
    static final int COMBO_BOX_WIDTH = 300;
    static final int COMBO_BOX_FONT_SIZE = 12;

    static final String DEFAULT_RESULT = "ここに結果が表示されます";
    static final int RESULT_FIELD_ROWS = 10;
    static final int RESULT_FIELD_COLUMNS = 40;

    class Grid {
        static final int X_DEFAULT = 0;
        static final int Y_DEFAULT = 0;
        static final int WIDTH = 1;
        static final int HEIGHT = 1;
    }

    class ButtonLabel {
        static final String SEARCH_CLASS = "Search class";
        static final String CALL_CONSTRUCTOR = "Call constructor";
        static final String OPEN_WINDOW = "Open object";

        static final String CHANGE_FIELD = "Change field";
        static final String CALL_METHOD = "Call method";
        static final String PARAMS = "Parameters";
        static final String CLEAR = "Clear";
        static final String CANCEL = "Cancel";
        static final String OK = "OK";
    }

    class TextLabel {
        static final String CLASS = "クラス名";
        static final String CONSTRUCTOR = "コンストラクタ";
        static final String OBJECT = "生成したオブジェクト";
        static final String FIELD = "フィールド";
        static final String METHOD = "メソッド";
        static final String ARRAY_SIZE = "配列の長さ";
        static final String ARRAY_INDEX = "配列のインデックス";
    }

    static class ParamTable {
        static final String[] ITEM_NAME = {"Name", "Type", "Value"};
        static final int COLUMNS = 3;   // 名前、型、値
        static final int VALUE_COLUMN = 2;   // 値の列
    }

    class Message {
        static final String SUCCESS = "成功しました";
        static final String SEARCH_CLASS = "クラスを検索します";
        static final String CALL_CONSTRUCTOR = "コンストラクタを呼び出します";
        static final String OPEN_WINDOW = "ウィンドウを開きます";
        static final String CHANGE_FIELD = "フィールドを変更します";
        static final String CALL_METHOD = "メソッドを呼び出します";
        static final String ERROR_EMPTY_VALUE = "値を入れてください";
    }
}
