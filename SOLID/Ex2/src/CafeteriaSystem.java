import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final ITaxPolicy taxPolicy;
    private final IDiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;
    private final IInvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(ITaxPolicy taxPolicy, IDiscountPolicy discountPolicy,
                           InvoiceFormatter formatter, IInvoiceStore store) {
        this.taxPolicy      = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.formatter      = formatter;
        this.store          = store;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        List<InvoiceLineData> lineData = new ArrayList<>();
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            lineData.add(new InvoiceLineData(item.name, l.qty, lineTotal));
        }

        double taxPct   = taxPolicy.taxPercent(customerType);
        double tax      = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());
        double total    = subtotal + tax - discount;

        InvoiceData data = new InvoiceData(invId, lineData, subtotal, taxPct, tax, discount, total);
        String printable = formatter.format(data);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
