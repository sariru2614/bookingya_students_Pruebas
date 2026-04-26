package bookingya.models;

public class Reservation {

    private String guestId;
    private String roomId;
    private String checkIn;
    private String checkOut;
    private Integer guestsCount;
    private String notes;

    public Reservation(String guestId, String roomId, String checkIn, String checkOut, Integer guestsCount, String notes) {
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestsCount = guestsCount;
        this.notes = notes;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public Integer getGuestsCount() {
        return guestsCount;
    }

    public String getNotes() {
        return notes;
    }
}