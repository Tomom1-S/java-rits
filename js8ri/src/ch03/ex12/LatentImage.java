package ch03.ex12;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class LatentImage {
    private Image in;
    private List<ColorTransformer> pendingOperations;

    private LatentImage(final Image in) {
        this.in = in;
        pendingOperations = new ArrayList<>();
    }

    public static LatentImage from(final Image in) {
        return new LatentImage(in);
    }

    public Image toImage() {
        final int width = (int) in.getWidth();
        final int height = (int) in.getHeight();
        final WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = in.getPixelReader().getColor(x, y);
                for (ColorTransformer op : pendingOperations) {
                    color = op.apply(x, y, color);
                }
                out.getPixelWriter().setColor(x, y, color);
            }
        }
        return out;
    }

    public LatentImage transform(final UnaryOperator<Color> f) {
        pendingOperations.add(ColorTransformer.convertUnaryOperator(f));
        return this;
    }

    public LatentImage transform(final ColorTransformer f) {
        pendingOperations.add(f);
        return this;
    }

}
