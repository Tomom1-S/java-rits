public class MenuItems {
    enum FontColor {
        BLACK("black"),
        WHITE("white"),
        RED("red"),
        GREEN("green"),
        BLUE("blue");

        String name;

        FontColor(String name) {
            this.name = name;
        }
    };

    enum BackgroundColor {
        BLACK("black"),
        WHITE("white"),
        RED("red"),
        GREEN("green"),
        BLUE("blue");

        String name;

        BackgroundColor(String name) {
            this.name = name;
        }
    }
}
