import java.util.List;

public class Board {
    private final int n;
    private final List<Jumpable> jumpables;

    public Board(int n, List<Jumpable> jumpables) {
        this.n = n;
        this.jumpables = jumpables;
    }

    public int updatePosition(int position) {
        for (Jumpable jumpable : jumpables) {
            if (jumpable.getStart() == position) {
                System.out.println("Encountered a " + jumpable.getClass().getSimpleName() + 
                    "! Jumping from " + position + " to " + jumpable.getEnd());
                return jumpable.getEnd();
            }
        }
        return position;
    }

    public int getMaxScore() {
        return n * n;
    }

    public int getSize() {
        return n;
    }
}
