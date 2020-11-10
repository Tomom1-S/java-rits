package ch03.ex11;

import ch03.ex05.ColorTransformer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.UnaryOperator;

public class Sample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final String path = "sample/food-5158702_640.jpg";
        final Image orgImg = new Image(new File(path).toURI().toString());
        final ColorTransformer blightEffect = convertUnaryOperator(Color::brighter);
        final ColorTransformer grayFrame = (x, y, colorAtXY) -> {
            final int framePixel = 10;
            if (x < framePixel || x >= orgImg.getWidth() - framePixel ||
                    y < framePixel || y >= orgImg.getHeight() - framePixel) {
                return Color.GRAY;
            }
            return colorAtXY;
        };
        final Image processedImg = transform(
                orgImg,
                combineColorTransformers(blightEffect, grayFrame)
        );

        final int height = 300;
        stage.setScene(new Scene(
                new HBox(resizeByHeight(orgImg, height),
                        resizeByHeight(processedImg, height))
        ));
        stage.show();
    }

    public static ColorTransformer combineColorTransformers(
            final ColorTransformer first,
            final ColorTransformer second
    ) {
        return (x, y, colorAtXY) ->
                first.apply(x, y, second.apply(x, y, colorAtXY));
    }

    public static ColorTransformer convertUnaryOperator(final UnaryOperator<Color> op) {
        return (x, y, colorAtXY) -> op.apply(colorAtXY);
    }

    private ImageView resizeByHeight(final Image image, final double height) {
        ImageView view = new ImageView(image);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        return view;
    }

    public static Image transform(final Image in, final ColorTransformer f) {
        final int width = (int) in.getWidth();
        final int height = (int) in.getHeight();
        final WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y,
                        f.apply(x, y, in.getPixelReader().getColor(x, y))
                );
            }
        }
        return out;
    }
}
