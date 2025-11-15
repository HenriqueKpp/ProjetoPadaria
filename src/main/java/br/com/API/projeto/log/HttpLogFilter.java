package br.com.API.projeto.log;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpLogFilter extends OncePerRequestFilter {

    private final LogService logs;

    public HttpLogFilter(LogService logs) { this.logs = logs; }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        long start = System.nanoTime();
        try {
            chain.doFilter(req, res);
        } finally {
            long ms = Math.round((System.nanoTime() - start) / 1_000_000.0);
            Map<String, Object> meta = new HashMap<>();
            meta.put("path", req.getRequestURI());
            meta.put("method", req.getMethod());
            meta.put("status", res.getStatus());
            meta.put("durationMs", ms);
            meta.put("when", Instant.now().toString());

            if (res.getStatus() >= 500) logs.error("http", "request finalizado com erro", meta);
            else logs.info("http", "request finalizado", meta);
        }
    }
}
