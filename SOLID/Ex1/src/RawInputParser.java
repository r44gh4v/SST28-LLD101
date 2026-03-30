import java.util.LinkedHashMap;
import java.util.Map;

public class RawInputParser {
    public ParsedInput parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        for (String part : raw.split(";")) {
            String[] t = part.split("=", 2);
            if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        }
        return new ParsedInput(
            kv.getOrDefault("name", ""),
            kv.getOrDefault("email", ""),
            kv.getOrDefault("phone", ""),
            kv.getOrDefault("program", "")
        );
    }
}
