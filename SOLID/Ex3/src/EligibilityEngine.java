import java.util.*;

public class EligibilityEngine {
    private final List<EligibilityRule> rules;
    private final ReportPrinter printer;
    private final IEligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules, ReportPrinter printer, IEligibilityStore store) {
        this.rules   = rules;
        this.printer = printer;
        this.store   = store;
    }

    public void runAndPrint(StudentProfile s) {
        EligibilityEngineResult r = evaluate(s);
        printer.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            rule.check(s).ifPresent(reasons::add);
        }
        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityEngineResult(status, reasons);
    }
}
