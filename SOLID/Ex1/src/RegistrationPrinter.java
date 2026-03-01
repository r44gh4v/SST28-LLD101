public class RegistrationPrinter {

    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printSuccess(StudentRecord rec, int totalCount) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + totalCount);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }

    public void printFailure(ValidationResult result) {
        System.out.println("ERROR: cannot register");
        for (String e : result.errors()) System.out.println("- " + e);
    }
}
