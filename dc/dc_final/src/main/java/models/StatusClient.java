package models;

import javafx.scene.paint.Color;
import lombok.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Value
public class StatusClient {
    private static final String TDR_ENDPOINT = "https://tokyodisneyresort.info/";
    private static final String USJ_ENDPOINT = "https://usjinfo.com/wait/realtime.php";
    private static final String ATTRIBUTE_ATTRACTION_NAME = "realtime-attr-name";
    private static final String ATTRIBUTE_ATTRACTION_CONDITION = "realtime-attr-condition";

    String endpoint;

    public StatusClient(final String endpoint) {
        this.endpoint = endpoint;
    }

    public StatusClient(final Park park) {
        switch (park) {
            case TDL:
            case TDS:
                endpoint = TDR_ENDPOINT;
                break;
            case USJ:
                endpoint = USJ_ENDPOINT;
                break;
            default:
                throw new IllegalArgumentException("Select correct park.");
        }
    }

    public List<Facility> fetchStatusList(final Park park, final FacilityType facilityType) {
        final String url = createUrl(park, facilityType);
        final Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        switch (facilityType) {
            case ATTRACTION_STANDBY:
            case GREETING:
            case RESTAURANT:
                return doc.selectFirst("main")
                        .selectFirst("div")
                        .selectFirst("article")
                        .children()
                        .stream()
                        .filter(element -> Objects.equals(element.className(), facilityType.getHtmlClass()))
                        .map(element -> fetchFacilityInstance(element.children().first().children(), facilityType))
                        .collect(Collectors.toList());
            case ATTRACTION_FASTPASS:
                return doc.selectFirst("main")
                        .selectFirst("div")
                        .children()
                        .stream()
                        .filter(element -> Objects.equals(element.className(), facilityType.getHtmlClass()))
                        .map(element -> fetchFacilityInstance(element.children().first().children(), facilityType))
                        .collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    private Facility fetchFacilityInstance(final Elements es, final FacilityType type) {
        final String[] name = new String[1];
        final String[] status = new String[1];
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

        switch (type) {
            case ATTRACTION_STANDBY:
            case ATTRACTION_FASTPASS:
                return new AttractionFacility(name[0], status[0]);
            case GREETING:
                return new GreetingFacility(name[0], status[0]);
            case RESTAURANT:
                return new RestaurantFacility(name[0], status[0]);
            default:
                return new AttractionFacility("none", "no data");
        }
    }

    private String createUrl(final Park park, final FacilityType facilityType) {
        if (Objects.isNull(park)) {
            return endpoint;
        }

        switch (park) {
            case TDL:
            case TDS:
                return String.format("%s%s.php?park=%s&order=area", endpoint, facilityType.getQueryValue(), park.getQueryValue());
            // e.g.
            // TDL: https://tokyodisneyresort.info/realtime.php?park=land&order=area
            default:
                return endpoint;
        }
    }
}
