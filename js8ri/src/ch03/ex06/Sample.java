package ch03.ex06;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.BiFunction;

public class Sample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final String path = "sample/food-5158702_640.jpg";
        final Image orgImg = new Image(new File(path).toURI().toString());
        final Image trsImg = transform(
                orgImg,
                (c, factor) -> c.deriveColor(0, 1, factor, 1),
                1.2);

        final int height = 300;
        stage.setScene(new Scene(
                new HBox(resizeByHeight(orgImg, height),
                        resizeByHeight(trsImg, height))
        ));
        stage.show();
    }

    private ImageView resizeByHeight(final Image image, final double height) {
        ImageView view = new ImageView(image);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        return view;
    }

    public static <T> Image transform(
            final Image in,
            BiFunction<Color, T, Color> f,
            T arg
    ) {
        final int width = (int) in.getWidth();
        final int height = (int) in.getHeight();
        final WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y,
                        f.apply(in.getPixelReader().getColor(x, y), arg)
                );
            }
        }
        return out;
    }
}
