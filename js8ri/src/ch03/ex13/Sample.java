package ch03.ex13;

import ch03.ex12.ColorTransformer;
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
//        final String path = "sample/tiger.jpg";
        final String path = "sample/coastal-5527726_640.jpg";
//        final String path = "sample/food-5158702_640.jpg";
        final Image orgImg = new Image(new File(path).toURI().toString());

        final ColorTransformer redFrame = (x, y, colorAtXY) -> {
            final int framePixel = 10;
            if (x < framePixel || x >= orgImg.getWidth() - framePixel ||
                    y < framePixel || y >= orgImg.getHeight() - framePixel) {
                return Color.RED;
            }
            return colorAtXY;
        };
        final ConvolutionFilter blur = (x, y, colors) -> {
            double red = 4 * colors[1][1].getRed()
                    - colors[0][1].getRed() - colors[2][1].getRed()
                    - colors[1][0].getRed() - colors[1][2].getRed();
            double green = 4 * colors[1][1].getGreen()
                    - colors[0][1].getGreen() - colors[2][1].getGreen()
                    - colors[1][0].getGreen() - colors[1][2].getGreen();
            double blue = 4 * colors[1][1].getBlue()
                    - colors[0][1].getBlue() - colors[2][1].getBlue()
                    - colors[1][0].getBlue() - colors[1][2].getBlue();
            return Color.color(
                    containColorRange(red),
                    containColorRange(green),
                    containColorRange(blue)
            );
        };

        final Image finalImg = LatentImage.from(orgImg)
                .transform(blur)
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

    /**
     * Color の RGB の有効範囲(0~1)に変換する
     *
     * @param value
     * @return 0~1に収まる値
     */
    private double containColorRange(final double value) {
        return value < 0 ? 0 : (value > 1 ? 1 : value);
    }
}
