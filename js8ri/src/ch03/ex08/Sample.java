package ch03.ex08;

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

public class Sample extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final Parameters params = getParameters();
        final String path = params.getRaw().get(0);
        final int framePixel = Integer.parseInt(params.getRaw().get(1));
        final Color frameColor = new Color(
                Double.parseDouble(params.getRaw().get(2)),
                Double.parseDouble(params.getRaw().get(3)),
                Double.parseDouble(params.getRaw().get(4)),
                1
        );

        final Image orgImg = new Image(new File(path).toURI().toString());
        final Image trsImg = transform(orgImg,
                createColorTransformer(
                        (int) orgImg.getWidth(), (int) orgImg.getHeight(),
                        framePixel, frameColor));

        final int height = 300;
        stage.setScene(new Scene(
                new HBox(resizeByHeight(orgImg, height),
                        resizeByHeight(trsImg, height))
        ));
        stage.show();
    }

    public static ColorTransformer createColorTransformer(
            final int width, final int height,
            final int framePixel,
            final Color frameColor

    ) {
        return (x, y, colorAtXY) -> {
            if (x < framePixel || x >= width - framePixel ||
                    y < framePixel || y >= height - framePixel) {
                return frameColor;
            }
            return colorAtXY;
        };
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
