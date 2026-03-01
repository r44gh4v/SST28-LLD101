import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) { this.minAttendance = minAttendance; }

    @Override
    public Optional<String> check(StudentProfile s) {
        return s.attendancePct < minAttendance
                ? Optional.of("attendance below " + minAttendance)
                : Optional.empty();
    }
}
