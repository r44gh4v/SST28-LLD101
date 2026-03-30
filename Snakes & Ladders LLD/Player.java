public class Player {
    private int id;
    private String name;
    private int score;
    private boolean hasCompleted;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.hasCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean hasCompleted() {
        return hasCompleted;
    }

    public void setCompleted(boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
    }

    public String toScore() {
        return name + " is at " + score;
    }

    @Override
    public String toString() {
        return "Player " + name + " (ID: " + id + ") - Score: " + score + (hasCompleted ? " [COMPLETED]" : "");
    }
}
