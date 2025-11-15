package br.com.API.projeto.log;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class LogService {
    private final LogRepository repo;
    public LogService(LogRepository repo) { this.repo = repo; }

    public void info(String category, String message, Map<String, Object> meta) { save("INFO", category, message, meta, 7); }
    public void warn(String category, String message, Map<String, Object> meta) { save("WARN", category, message, meta, 30); }
    public void error(String category, String message, Map<String, Object> meta) { save("ERROR", category, message, meta, 90); }

    private void save(String level, String category, String message, Map<String, Object> meta, int keepDays) {
        repo.save(new LogEntry(level, category, message, meta, Instant.now().plus(keepDays, ChronoUnit.DAYS)));
    }
}
