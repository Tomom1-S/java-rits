package ch16.ex11;

public class Game {
    public static void main(String[] args) {
        String name;    // クラス名
        while ((name = getNextPlayer()) != null) {
//            try {
//                PlayerLoader loader = new PlayerLoader();
//                Class<? extends Player> classOf =
//                        loader.loadClass(name).asSubClass(Player.class);
//                Player player = classOf.newInstance();
//                Game game = new Game();
//                player.play(game);
//                game.reportScore(name);
//            } catch (Exception e) {
//                reportException(name, e);
//            }
        }
    }

    private void reportScore(String name) {
    }

    private static String getNextPlayer() {
        String name = "foo";
        return name;
    }

    private static void reportException(String name, Exception e) {
        System.err.println(name + ": " + e.getMessage());
    }
}
