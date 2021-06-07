package features;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;

@RequiredArgsConstructor
@Getter
class AdTestItem {
    private final String id;
    private final String type;
    private final String format;
    private final String advertiser;
    private final String price;
    private final String price_type;
    private final String url;
    private final String title;
    private final String details;

    public static AdTestItem FromMap(Map<String, String> map) {
        return new AdTestItem(map.get("id"),
                map.get("type"),
                map.get("format"),
                map.get("advertiser"),
                map.get("price"),
                map.get("price_type"),
                map.get("url"),
                map.get("title"),
                map.get("details"));
    }

    public String toStringInput() {
        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                getId(), getType(), getFormat(), getAdvertiser(), getPrice(), getPrice_type(), getUrl(), getTitle(), getDetails());
    }

    public String convertToPipeRow() {
        var fields = Arrays.asList(
                this.getId(),
                this.getType(),
                this.getFormat(),
                this.getAdvertiser(),
                this.getPrice(),
                this.getPrice_type(),
                this.getUrl(),
                this.getTitle(),
                this.getDetails()
        );

        return String.join("|", fields);
    }
}
