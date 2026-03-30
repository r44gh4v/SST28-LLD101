public class Ladder implements Jumpable {
    private final int start;
    private final int end;

    public Ladder(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("Ladder start must be less than end");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getEnd() {
        return end;
    }
}
