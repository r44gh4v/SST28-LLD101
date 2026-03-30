public class Gate {
    private String gateId;
    private int level;

    public Gate(String gateId, int level) {
        this.gateId = gateId;
        this.level = level;
    }

    public String getGateId() { return gateId; }
    public int getLevel() { return level; }
}
