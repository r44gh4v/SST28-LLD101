import java.util.Optional;

public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) { this.minCgr = minCgr; }

    @Override
    public Optional<String> check(StudentProfile s) {
        return s.cgr < minCgr ? Optional.of("CGR below " + minCgr) : Optional.empty();
    }
}
