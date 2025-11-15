package br.com.API.projeto.log;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document("logs")
public class LogEntry {
    @Id private String id;
    @Indexed private String level;     // INFO, WARN, ERROR
    private String category;           // http, negocio, etc.
    private String message;
    private Map<String, Object> meta;
    private Instant timestamp = Instant.now();

    // TTL: documento expira quando essa data chega
    @Indexed(expireAfterSeconds = 0)
    private Instant expiresAt;

    public LogEntry() {}
    public LogEntry(String level, String category, String message, Map<String, Object> meta, Instant expiresAt) {
        this.level = level;
        this.category = category;
        this.message = message;
        this.meta = meta;
        this.expiresAt = expiresAt;
    }
    // getters/setters
}
