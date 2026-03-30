import java.util.Optional;

public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public Optional<String> check(StudentProfile s) {
        return s.disciplinaryFlag != LegacyFlags.NONE
                ? Optional.of("disciplinary flag present")
                : Optional.empty();
    }
}
