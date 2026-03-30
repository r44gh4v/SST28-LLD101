public class Snake implements Jumpable {
    private final int start;
    private final int end;

    public Snake(int start, int end) {
        if (start <= end) {
            throw new IllegalArgumentException("Snake start must be greater than end");
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
