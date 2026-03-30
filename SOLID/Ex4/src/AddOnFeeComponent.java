public class AddOnFeeComponent implements FeeComponent {
    private final Money monthly;

    public AddOnFeeComponent(double monthly) {
        this.monthly = new Money(monthly);
    }

    @Override public Money monthlyContribution() { return monthly; }
    @Override public Money depositContribution() { return new Money(0.0); }
}
