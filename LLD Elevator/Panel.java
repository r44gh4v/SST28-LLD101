import java.util.HashMap;
import java.util.Map;

public class Panel {
    private Map<Integer, Button> buttons;

    public Panel(int minFloor, int maxFloor) {
        buttons = new HashMap<>();
        for (int i = minFloor; i <= maxFloor; i++) {
            buttons.put(i, new Button(i));
        }
    }

    public void pressButton(int floor) {
        if (buttons.containsKey(floor)) {
            buttons.get(floor).press();
        }
    }

    public void resetButton(int floor) {
        if (buttons.containsKey(floor)) {
            buttons.get(floor).reset();
        }
    }
}