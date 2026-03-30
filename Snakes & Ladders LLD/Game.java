import java.util.List;

public class Game {
    private final IMode mode;
    private final List<Player> players;

    public Game(IMode mode, List<Player> players) {
        this.mode = mode;
        this.players = players;
    }

    public void run() {
        boolean gameOngoing = true;
        System.out.println("=============== GAME STARTED ===============");
        
        while (gameOngoing) {
            gameOngoing = false;
            
            for (Player player : players) {
                if (!player.hasCompleted()) {
                    mode.makeTurn(player);
                }
                
                // Note: The loop could alter player state to completed, 
                // check completion status.
                if (!player.hasCompleted()) {
                    gameOngoing = true;
                }
            }
            
            long incompleteCount = players.stream().filter(p -> !p.hasCompleted()).count();
            if (incompleteCount == 0) {
                System.out.println("All players have finished! Game Over!");
                break;
            } else if (incompleteCount == 1 && players.size() > 1) {
                System.out.println("Game Over! Remaining player: " + 
                    players.stream().filter(p -> !p.hasCompleted()).findFirst().get().getName() + " could not finish.");
                break;
            }
        }
    }
}
