public class Movie {
    private String id;
    private String name;
    private int durationInMins;

    public Movie(String id, String name, int durationInMins) {
        this.id = id;
        this.name = name;
        this.durationInMins = durationInMins;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}