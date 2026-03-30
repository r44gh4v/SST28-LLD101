public class Seat {
    private String id;
    private int rowNo;
    private int seatNo;
    private SeatCategory category;

    public Seat(String id, int rowNo, int seatNo, SeatCategory category) {
        this.id = id;
        this.rowNo = rowNo;
        this.seatNo = seatNo;
        this.category = category;
    }

    public String getId() { return id; }
    public SeatCategory getCategory() { return category; }
}