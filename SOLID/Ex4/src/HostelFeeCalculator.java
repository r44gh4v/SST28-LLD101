import java.util.*;

public class HostelFeeCalculator {
    private final ReceiptPrinter printer;
    private final IBookingRepo repo;

    public HostelFeeCalculator(ReceiptPrinter printer, IBookingRepo repo) {
        this.printer = printer;
        this.repo    = repo;
    }

    public void process(BookingRequest req, List<FeeComponent> components) {
        Money monthly = new Money(0.0);
        Money deposit = new Money(0.0);
        for (FeeComponent c : components) {
            monthly = monthly.plus(c.monthlyContribution());
            deposit = deposit.plus(c.depositContribution());
        }

        printer.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}
