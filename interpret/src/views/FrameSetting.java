package views;

import java.awt.*;

public final class FrameSetting {
    static final String FRAME_TITLE = "Interpret プログラム";
    static final String FRAME_PARAMS = "Parameters";
    static final String FRAME_FIELD = "Field";

    static final String FRAME_OBJECT = "[OBJECT]";
    static final String FRAME_ARRAY = "[ARRAY]";

    static final Color BG_COLOR = Color.LIGHT_GRAY;
    static final Color ACCENT_COLOR = Color.RED;
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
        static final String CREATE_ARRAY = "Create array";
        static final String CALL_CONSTRUCTOR = "Call constructor";

        static final String OPEN_OBJECT_WINDOW = "Open object";
        static final String OPEN_ARRAY_WINDOW = "Open array";

        static final String CHANGE_FIELD = "Change field";
        static final String CALL_METHOD = "Call method";
        static final String PARAMS = "Parameters";
        static final String CLEAR = "Clear";
        static final String CANCEL = "Cancel";
        static final String OK = "OK";

        static final String GET_ARRAY_ELEMENT = "Get element";
        static final String SET_ARRAY_ELEMENT = "Set element";
    }

    class TextLabel {
        static final String CLASS = "クラス名";
        static final String CONSTRUCTOR = "コンストラクタ";
        static final String FIELD = "フィールド";
        static final String METHOD = "メソッド";

        static final String ARRAY = "配列";
        static final String ARRAY_TYPE = "型名";
        static final String ARRAY_SIZE = "長さ";
        static final String ARRAY_INDEX = "インデックス";
        static final String ARRAY_ELEMENT = "オブジェクト";

        static final String HISTORY = "履歴";
        static final String OBJECT_HISTORY = "生成したオブジェクト";
        static final String ARRAY_HISTORY = "生成した配列";
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
        static final String CREATE_ARRAY = "配列を作ります";

        static final String CHANGE_FIELD = "フィールドを変更します";
        static final String CALL_METHOD = "メソッドを呼び出します";

        static final String GET_ELEMENT = "配列の要素を取得します";
        static final String SET_ELEMENT = "配列の要素を設定します";
        static final String EMPTY_ELEMENT = "配列の要素が設定されていません";

        static final String ERROR_EMPTY_VALUE = "値を入れてください";
        static final String INVALID_VALUE = "不正な値が入っています";
    }
}
