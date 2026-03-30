public interface IDiscountPolicy {
    double discountAmount(String customerType, double subtotal, int lineCount);
}
