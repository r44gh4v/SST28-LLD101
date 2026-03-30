import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String csv = "title,body\n" + csvField(req.title) + "," + csvField(req.body) + "\n";
        return ExportResult.ok("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String csvField(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\n") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
