package models;

import lombok.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
public class WaitingTime {
    private static final String TDR_ENDPOINT = "https://tokyodisneyresort.info/";
    private static final String USJ_ENDPOINT = "https://usjinfo.com/wait/realtime.php";
    private static final String ATTRIBUTE_ATTRACTION_NAME = "realtime-attr-name";
    private static final String ATTRIBUTE_ATTRACTION_CONDITION = "realtime-attr-condition";

    String endpoint;

    public WaitingTime(final String endpoint) {
        this.endpoint = endpoint;
    }

    public WaitingTime(final Park park) {
        if (park == Park.USJ) {
            endpoint = USJ_ENDPOINT;
        } else if (park == Park.TDL || park == Park.TDS) {
            endpoint = TDR_ENDPOINT;
        } else {
            throw new IllegalArgumentException("Select correct park.");
        }
    }

    public List<Facility> fetchWaitingTimes(final Park park, final FacilityType facilityType) {
        final String url = createUrl(park, facilityType);
        final Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (final IOException e) {
            // TODO Handle exception
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return doc.selectFirst("main")
                .selectFirst("div")
                .selectFirst("article")
                .children()
                .stream()
                .filter(element -> Objects.equals(element.className(), facilityType.getHtmlClass()))
                .map((Function<Element, Facility>) element -> {
                    final Elements es = element.children().first().children();

                    final String[] name = new String[1];
                    final String[] status = new String[1];
                    // TODO 他の施設（レストラン・グリでも同じようにデータをまとめる）
                    es.forEach(e -> {
                        final Attribute attribute = e.attributes().asList().get(0);
                        switch (attribute.getValue()) {
                            case ATTRIBUTE_ATTRACTION_NAME:
                                name[0] = e.text();
                                break;
                            case ATTRIBUTE_ATTRACTION_CONDITION:
                                status[0] = e.text();
                        }
                    });
                    return new AttractionFacility(name[0], status[0]);
                })
                .collect(Collectors.toList());
    }

    private String createUrl(final Park park, final FacilityType facilityType) {
        if (park == Park.TDL || park == Park.TDS) {
            return String.format("%s%s.php?park=%s&order=area", endpoint, facilityType.getValue(), park.getValue());
            // e.g.
            // TDL: https://tokyodisneyresort.info/realtime.php?park=land&order=area
        }
        return endpoint;
    }
}
