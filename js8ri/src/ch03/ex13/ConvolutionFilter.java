package ch03.ex13;

import ch03.ex12.ColorTransformer;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

public interface ConvolutionFilter {
    /**
     * フィルタを適用する
     * @param x 横位置
     * @param y 縦位置
     * @param colors ターゲットの周囲の Color ((1, 1) がターゲット)
     * @return 変換した色
     */
    Color apply(int x, int y, Color[][] colors);

    static ConvolutionFilter convert(final UnaryOperator<Color> op) {
        return (_x, _y, colors) -> op.apply(colors[1][1]);
    }

    static ConvolutionFilter convert(final ColorTransformer op) {
        return (x, y, colors) -> op.apply(x, y, colors[1][1]);
    }
}
