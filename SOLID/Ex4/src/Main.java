import java.util.*;

public class Main {
    private static final Map<Integer, RoomFeeComponent> ROOM_FEES = new LinkedHashMap<>();
    private static final Map<AddOn, AddOnFeeComponent> ADDON_FEES = new LinkedHashMap<>();

    static {
        ROOM_FEES.put(LegacyRoomTypes.SINGLE, new RoomFeeComponent(14000.0, 5000.0));
        ROOM_FEES.put(LegacyRoomTypes.DOUBLE, new RoomFeeComponent(15000.0, 5000.0));
        ROOM_FEES.put(LegacyRoomTypes.TRIPLE, new RoomFeeComponent(12000.0, 5000.0));
        ROOM_FEES.put(LegacyRoomTypes.DELUXE, new RoomFeeComponent(16000.0, 5000.0));

        ADDON_FEES.put(AddOn.MESS,    new AddOnFeeComponent(1000.0));
        ADDON_FEES.put(AddOn.LAUNDRY, new AddOnFeeComponent(500.0));
        ADDON_FEES.put(AddOn.GYM,     new AddOnFeeComponent(300.0));
    }

    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        List<FeeComponent> components = new ArrayList<>();
        components.add(ROOM_FEES.get(req.roomType));
        for (AddOn a : req.addOns) components.add(ADDON_FEES.get(a));

        HostelFeeCalculator calc = new HostelFeeCalculator(new ReceiptPrinter(), new FakeBookingRepo());
        calc.process(req, components);
    }
}
