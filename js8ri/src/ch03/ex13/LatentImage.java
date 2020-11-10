package ch03.ex13;

import ch03.ex12.ColorTransformer;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class LatentImage {
    private Image in;
    private List<ConvolutionFilter> pendingOperations;

    private LatentImage(final Image in) {
        this.in = in;
        pendingOperations = new ArrayList<>();
    }

    public static LatentImage from(final Image in) {
        return new LatentImage(in);
    }

    public Image toImage() throws IllegalAccessException {
        final int width = (int) in.getWidth();
        final int height = (int) in.getHeight();

        // 初期画像を生成
        Color[][] before = fillEdge(createBiggerArray(convertImageToArray(in)));
        Color[][] after = before;

        for (ConvolutionFilter filter : pendingOperations) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    final int X = x + 1;
                    final int Y = y + 1;
                    after[X][Y] = filter.apply(X, Y, extractNeighbors(before, X, Y));
                }
            }
            // 処理後の画像の縁を埋めて、次の処理に使う
            before = fillEdge(after);
        }

        // 結果用の画像を生成
        return convertArrayToImage(createSmallerArray(after));
    }

    private Color[][] convertImageToArray(final Image in) {
        final int width = (int) in.getWidth();
        final int height = (int) in.getHeight();
        Color[][] result = new Color[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result[x][y] = in.getPixelReader().getColor(x, y);
            }
        }
        return result;
    }

    private Image convertArrayToImage(final Color[][] in) {
        final int width = in.length;
        final int height = in[0].length;
        final WritableImage result = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result.getPixelWriter().setColor(x, y, in[x][y]);
            }
        }
        return result;
    }

    /**
     * フィルタ処理用に縦横2ピクセル大きい Color の配列を作る
     *
     * @param in W x H のサイズの Color の配列
     * @return (W + 2) x (H + 2) のサイズの Color の配列(縁は null)
     */
    private Color[][] createBiggerArray(final Color[][] in) {
        final int width = in.length;
        final int height = in[0].length;
        Color[][] result = new Color[width + 2][height + 2];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result[x + 1][y + 1] = in[x][y];
            }
        }
        return result;
    }

    /**
     * フィルタ処理用に縦横2ピクセル小さい Color の配列を作る
     *
     * @param in W x H のサイズの Color の配列
     * @return (W - 2) x (H - 2) のサイズの Color の配列
     */
    private Color[][] createSmallerArray(final Color[][] in) {
        final int width = in.length - 2;
        final int height = in[0].length - 2;
        Color[][] result = new Color[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result[x][y] = in[x + 1][y + 1];
            }
        }
        return result;
    }

    /**
     * フィルタ処理用に縁を塗りつぶす
     *
     * @param in Color の配列
     * @return Color の配列(縁は隣のピクセルと同じ値)
     */
    private Color[][] fillEdge(final Color[][] in) {
        final int width = in.length;
        final int height = in[0].length;
        Color[][] result = in;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x > 0 && x < width - 1 && y > 0 && y < height - 1) {
                    result[x][y] = in[x][y];
                    continue;
                }

                // 元の画像の外は隣のピクセルと同じにする
                if (x == 0 && y == 0) {
                    result[x][y] = in[x + 1][y + 1];
                } else if (x == 0 && y == height - 1) {
                    result[x][y] = in[x + 1][y - 1];
                } else if (x == width - 1 && y == 0) {
                    result[x][y] = in[x - 1][y + 1];
                } else if (x == width - 1 && y == height - 1) {
                    result[x][y] = in[x - 1][y - 1];
                } else if (x == 0) {
                    result[x][y] = in[x + 1][y];
                } else if (x == width - 1) {
                    result[x][y] = in[x - 1][y];
                } else if (y == 0) {
                    result[x][y] = in[x][y + 1];
                } else if (y == height - 1) {
                    result[x][y] = in[x][y - 1];
                }
            }
        }
        return result;
    }

    private Color[][] extractNeighbors(
            final Color[][] in,
            final int x, final int y) throws IllegalAccessException {
        final int width = in.length;
        final int height = in[0].length;
        final int size = 3;

        if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
            throw new IllegalAccessException("0 < x < width - 1 AND 0 < y < height - 1");
        }

        Color[][] result = new Color[size][size];
        result[0][0] = in[x - 1][y - 1];
        result[0][1] = in[x - 1][y];
        result[0][2] = in[x - 1][y + 1];
        result[1][0] = in[x][y - 1];
        result[1][1] = in[x][y];
        result[1][2] = in[x][y + 1];
        result[2][0] = in[x + 1][y - 1];
        result[2][1] = in[x + 1][y];
        result[2][2] = in[x + 1][y + 1];
        return result;
    }

    public LatentImage transform(final ConvolutionFilter f) {
        pendingOperations.add(f);
        return this;
    }

    public LatentImage transform(final UnaryOperator<Color> f) {
        return transform(ConvolutionFilter.convert(f));
    }

    public LatentImage transform(final ColorTransformer f) {
        return transform(ConvolutionFilter.convert(f));
    }

}
