package ch03.ex12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Sample extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final String path = "sample/food-5158702_640.jpg";
        final Image orgImg = new Image(new File(path).toURI().toString());
        final ColorTransformer redFrame = (x, y, colorAtXY) -> {
            final int framePixel = 10;
            if (x < framePixel || x >= orgImg.getWidth() - framePixel ||
                    y < framePixel || y >= orgImg.getHeight() - framePixel) {
                return Color.RED;
            }
            return colorAtXY;
        };
        final Image finalImg = LatentImage.from(orgImg)
                .transform(Color::brighter)
                .transform(Color::grayscale)
                .transform(redFrame)
                .toImage();

        final int height = 300;
        stage.setScene(new Scene(
                new HBox(resizeByHeight(orgImg, height),
                        resizeByHeight(finalImg, height))
        ));
        stage.show();
    }

    private ImageView resizeByHeight(final Image image, final double height) {
        ImageView view = new ImageView(image);
        view.setFitHeight(height);
        view.setPreserveRatio(true);
        return view;
    }
}
