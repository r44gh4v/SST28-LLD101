import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Snakes and Ladders LLD!");

        System.out.print("Enter board size n (for an n x n board): ");
        int boardSize = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter number of players x: ");
        int numPlayers = Integer.parseInt(scanner.nextLine().trim());

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.nextLine().trim();
            players.add(new Player(i, name));
        }

        int maxPosition = boardSize * boardSize;
        List<Jumpable> jumpables = generateJumpables(boardSize, maxPosition);
        
        Board board = new Board(boardSize, jumpables);
        Dice dice = new Dice(6);
        IDisplay displayer = new Displayer(board, players);
        
        System.out.println("Select Game Mode (easy/hard):");
        System.out.print("Your choice: ");
        String choice = scanner.nextLine().trim();
        
        IMode mode;
        if ("hard".equalsIgnoreCase(choice)) {
            mode = new DifficultMode(dice, board, displayer, scanner);
            System.out.println("Hard Mode selected (Max 3 consecutive 6s).");
        } else {
            mode = new EasyMode(dice, board, displayer, scanner);
            System.out.println("Easy Mode selected (Unlimited 6s).");
        }
        
        Game game = new Game(mode, players);
        
        // Show initial board state
        displayer.display();
        
        // Run game loop
        game.run();
        
        scanner.close();
    }

    private static List<Jumpable> generateJumpables(int n, int maxPosition) {
        List<Jumpable> jumpables = new ArrayList<>();
        Set<Integer> usedPositions = new HashSet<>();
        Random random = new Random();

        // Generate exactly n snakes
        int snakesCreated = 0;
        while (snakesCreated < n) {
            int head = random.nextInt(maxPosition - 2) + 2; // 2 to maxPosition - 1
            int tail = random.nextInt(head - 1) + 1; // 1 to head - 1

            if (!usedPositions.contains(head) && !usedPositions.contains(tail)) {
                jumpables.add(new Snake(head, tail));
                usedPositions.add(head);
                usedPositions.add(tail);
                snakesCreated++;
            }
        }

        // Generate exactly n ladders
        int laddersCreated = 0;
        while (laddersCreated < n) {
            int start = random.nextInt(maxPosition - 2) + 2; // 2 to maxPosition - 1
            int end = random.nextInt(maxPosition - start) + start + 1; // start + 1 to maxPosition

            if (!usedPositions.contains(start) && !usedPositions.contains(end)) {
                jumpables.add(new Ladder(start, end));
                usedPositions.add(start);
                usedPositions.add(end);
                laddersCreated++;
            }
        }

        return jumpables;
    }
}
