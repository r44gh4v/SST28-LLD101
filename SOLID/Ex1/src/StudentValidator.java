import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentValidator {
    private static final Set<String> ALLOWED_PROGRAMS = Set.of("CSE", "AI", "SWE");

    public ValidationResult validate(ParsedInput input) {
        List<String> errors = new ArrayList<>();
        if (input.name.isBlank()) errors.add("name is required");
        if (input.email.isBlank() || !input.email.contains("@")) errors.add("email is invalid");
        if (input.phone.isBlank() || !input.phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!ALLOWED_PROGRAMS.contains(input.program)) errors.add("program is invalid");
        return new ValidationResult(errors);
    }
}
