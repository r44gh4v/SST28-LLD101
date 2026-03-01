public class FakeEligibilityStore implements IEligibilityStore {
    @Override
    public void save(String roll, String status) {
        System.out.println("Saved evaluation for roll=" + roll);
    }
}
