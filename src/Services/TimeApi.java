package Services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class TimeApi {

    private String datetime;

    public void setDate() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://worldtimeapi.org/api/timezone/America/Guayaquil"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(jsonString -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode rootNode = objectMapper.readTree(jsonString);

                        // Suponiendo que el JSON tiene un campo 'datetime'
                        this.datetime = rootNode.path("datetime").asText();

                        // Aquí puedes hacer lo que necesites con 'datetime'
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                )
                .join();
    }

    public String getDatetime() {
        return datetime;
    }

    private String convertToMySQLDateTimeFormat() {
        setDate();
        OffsetDateTime odt = OffsetDateTime.parse(getDatetime());
        return odt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Timestamp convertToSQLTimestamp() {
        String fec_hor = convertToMySQLDateTimeFormat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(fec_hor, formatter);
        return Timestamp.valueOf(ldt);
    }

}
