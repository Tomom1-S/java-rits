package ch03.ex10;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.function.UnaryOperator;

public class Sample {
    public static void main(final String[] args) {
        final String path = "sample/food-5158702_640.jpg";
        final Image image = new Image(new File(path).toURI().toString());
        UnaryOperator<Color> op = Color::brighter;
//        Image finalImage =
//                transform(image, op.compose(Color::grayscale));
        Image finalImage =
                transform(image, color -> color.brighter().grayscale());

    }

    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width  = (int)in.getWidth();
        int height = (int)in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(
                        x, y,
                        f.apply(in.getPixelReader().getColor(x, y))
                );
            }
        }
        return out;
    }
}
