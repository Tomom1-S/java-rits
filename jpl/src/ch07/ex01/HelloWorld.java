package ch07.ex01;

public class HelloWorld {
    public static void main(String[] args) {
        String str = new String(
                "\u0048" +
                        "\u0065" +
                        "\u006c" +
                        "\u006c" +
                        "\u006f" +
						"\u002c" +
                        "\u0020" +
                        "\u0057" +
                        "\u006f" +
                        "\u0072" +
                        "\u006c" +
                        "\u0064");
        System.out.println(str);
    }
}
