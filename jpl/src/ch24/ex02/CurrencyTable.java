package ch24.ex02;

import java.nio.charset.Charset;
import java.util.*;

public class CurrencyTable {
    final static List<Locale> locales = Arrays.asList(
            Locale.US,
            Locale.CANADA,
            Locale.CHINA,
            Locale.TAIWAN,
            new Locale("zh", "HK"),
            Locale.JAPAN
    );

    final static List<Currency> currencies = Arrays.asList(
            Currency.getInstance("USD"),
            Currency.getInstance("CAD"),    // カナダドル
            Currency.getInstance("CNY"),    // 人民元
            Currency.getInstance("TWD"),    // 台湾ドル
            Currency.getInstance("HKD"),    // 香港ドル
            Currency.getInstance("JPY")
    );

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        // Item name
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%21s|", ""));
        for (Currency cur : currencies) {
            final String str = String.format("| %s", format(cur.getDisplayName(), 18));
            sb.append(str);
        }
        System.out.println(sb);

        for (int i = 0; i < 20 * 7; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Symbols
        for (Locale loc : locales) {
            sb.setLength(0);    // Initialize StringBuilder

            sb.append(String.format("%20s |", loc.getDisplayCountry()));
            for (Currency cur : currencies) {
                final String str = String.format("| %s", format(cur.getSymbol(loc), 18));
                sb.append(str);
            }
            System.out.println(sb);
        }
    }

    private static String format(String target, int length) {
        int byteDiff = (getByteLength(target, Charset.forName("UTF-8")) - target.length()) / 2;
        return String.format("%-" + (length - byteDiff) + "s", target);
    }

    private static int getByteLength(String string, Charset charset) {
        return string.getBytes(charset).length;
    }
}
