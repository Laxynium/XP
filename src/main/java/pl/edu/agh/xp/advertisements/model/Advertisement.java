package pl.edu.agh.xp.advertisements.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {

    private Integer id;
    private String type;
    private String format;
    private String advertiser;
    private String price;
    private String priceType;
    private String url;
    private String title;
    private String details;

    public Advertisement(String[] params) {
        this.id = Integer.valueOf(params[0]);
        this.type = params[1];
        this.format = params[2];
        this.advertiser = params[3];
        this.price = params[4];
        this.priceType = params[5];
        this.url = params[6];
        this.title = params[7];
        this.details = params[8];
    }
}
