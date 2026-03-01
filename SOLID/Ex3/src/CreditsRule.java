import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) { this.minCredits = minCredits; }

    @Override
    public Optional<String> check(StudentProfile s) {
        return s.earnedCredits < minCredits
                ? Optional.of("credits below " + minCredits)
                : Optional.empty();
    }
}
