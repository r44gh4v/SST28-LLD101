import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String id;
    private String name;
    private String cityId;
    private List<Screen> screens;

    public Theater(String id, String name, String cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        this.screens.add(screen);
    }

    public String getId() { return id; }
    public String getCityId() { return cityId; }
    public List<Screen> getScreens() { return screens; }
}