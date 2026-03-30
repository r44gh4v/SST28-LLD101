import java.util.List;

public class Displayer implements IDisplay {
    private final Board board;
    private final List<Player> players;

    public Displayer(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    @Override
    public void display() {
        int n = board.getSize();
        char[][] grid = new char[n][n];
        
        // Initialize with default char
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '.';
            }
        }

        // Place players
        for (Player p : players) {
            int score = p.getScore();
            if (score > 0) {
                int pos = score - 1; // 0-based indexing for the grid calculations
                int row = pos / n;
                int col = pos % n;
                
                // Classic SnL zig-zag path handling
                if (row % 2 != 0) {
                    col = n - 1 - col;
                }
                
                // Simple collision handling: if multiple on same square, it overwrites
                grid[row][col] = (char) ('A' + p.getId());
            }
        }

        System.out.println("-------------------------------------------------");
        System.out.println("Board:");
        System.out.println("-------------------------------------------------");
        // Print from top to bottom
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------------------");
        System.out.println("Scores:");
        for (Player p : players) {
            System.out.println(p.toScore());
        }
        System.out.println("-------------------------------------------------");
    }
}
