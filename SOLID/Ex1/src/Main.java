public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");

        StudentStore store = new FakeDb();
        OnboardingService svc = new OnboardingService(
            store,
            new RawInputParser(),
            new StudentValidator(),
            new RegistrationPrinter()
        );

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(store));
    }
}
