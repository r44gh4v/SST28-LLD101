import java.util.Scanner;

public class DifficultMode implements IMode {
    private final Dice dice;
    private final Board board;
    private final IDisplay displayer;
    private final Scanner scanner;
    private static final int MAX_ROLLS = 3;

    public DifficultMode(Dice dice, Board board, IDisplay displayer, Scanner scanner) {
        this.dice = dice;
        this.board = board;
        this.displayer = displayer;
        this.scanner = scanner;
    }

    @Override
    public void makeTurn(Player p) {
        System.out.println("\n==> " + p.getName() + "'s Turn");
        int sixCount = 0;
        boolean rolledSix;
        
        do {
            System.out.print("[Press Enter to roll the dice...]");
            scanner.nextLine();
            
            int roll = dice.roll();
            System.out.println(p.getName() + " rolled a " + roll);
            
            rolledSix = (roll == 6);
            if (rolledSix) {
                sixCount++;
                if (sixCount == MAX_ROLLS) {
                    System.out.println("Rolled 6 three times! Turn discarded.");
                    break;
                }
            } else {
                sixCount = 0;
            }

            int newScore = p.getScore() + roll;
            if (newScore > board.getMaxScore()) {
                System.out.println("Score exceeds " + board.getMaxScore() + "! Turn discarded.");
            } else if (newScore == board.getMaxScore()) {
                p.setScore(newScore);
                p.setCompleted(true);
                System.out.println("\n*** " + p.getName() + " HAS REACHED THE FINISH LINE! ***\n");
                displayer.display();
                break;
            } else {
                newScore = board.updatePosition(newScore);
                p.setScore(newScore);
            }
            
            displayer.display();
            
            if (rolledSix && sixCount < MAX_ROLLS) {
                System.out.println("Rolled a 6! " + p.getName() + " gets another roll.");
            }
        } while (rolledSix && sixCount < MAX_ROLLS);
    }
}
