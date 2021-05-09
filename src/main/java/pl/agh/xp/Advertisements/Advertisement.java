class Advertisement {

    private Integer id;
    private String type, format, advertiser, price, price_type, url, title, details;

    Advertisement(String[] params){
        this.id = Integer.valueOf(params[0]);
        this.type = params[1];
        this.format = params[2];
        this.advertiser = params[3];
        this.price = params[4];
        this.price_type = params[5];
        this.url = params[6];
        this.title = params[7];
        this.details = params[8];
    }

}
