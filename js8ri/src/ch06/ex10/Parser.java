package ch06.ex10;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static List<URL> getLinks(final String page) {
        // https://stackoverflow.com/questions/5120171/extract-links-from-a-web-page
        final Pattern pattern = Pattern.compile("(<a[^>]+>.+?</a>)",
                Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(page);
        final List<URL> result = new ArrayList<>();
        while (matcher.find()) {
            final String str = matcher.group();
            final String urlStr = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
            try {
                result.add(new URL(urlStr));
            } catch (MalformedURLException e) {
                // 間違った URL が含まれていたら無視
            }
        }
        return result;
    }
}
