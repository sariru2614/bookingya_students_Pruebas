package bookingya.models;

public class Room {

    private String code;
    private String name;
    private String city;
    private Integer maxGuests;
    private Integer nightlyPrice;
    private Boolean available;

    public Room(String code, String name, String city, Integer maxGuests, Integer nightlyPrice, Boolean available) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.maxGuests = maxGuests;
        this.nightlyPrice = nightlyPrice;
        this.available = available;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public Integer getMaxGuests() { return maxGuests; }
    public Integer getNightlyPrice() { return nightlyPrice; }
    public Boolean getAvailable() { return available; }
}