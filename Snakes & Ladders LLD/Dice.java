public class Dice {
    private final int size;

    public Dice(int size) {
        this.size = size;
    }

    public int roll() {
        return (int) Math.floor(Math.random() * size + 1);
    }
}
