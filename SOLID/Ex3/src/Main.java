import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        RuleInput config = new RuleInput();

        EligibilityEngine engine = new EligibilityEngine(
                List.of(
                        new DisciplinaryFlagRule(),
                        new CgrRule(config.minCgr),
                        new AttendanceRule(config.minAttendance),
                        new CreditsRule(config.minCredits)
                ),
                new ReportPrinter(),
                new FakeEligibilityStore()
        );

        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        engine.runAndPrint(s);
    }
}
