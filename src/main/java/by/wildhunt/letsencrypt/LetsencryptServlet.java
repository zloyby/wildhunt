package by.wildhunt.letsencrypt;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LetsencryptServlet extends HttpServlet {
    static final String PREFIX = "/.well-known/acme-challenge/";
    static final String KEY = "DX3HnI7PD_wKodU-dFiqzjy5x0yOX2qw1JWpdMH0b8E";
    static final String VALUE = "DX3HnI7PD_wKodU-dFiqzjy5x0yOX2qw1JWpdMH0b8E.sAOLUWZaF3jYw6Y1zTBuH1stZpWlTXy2zJVfwz3VAok";

    private static final Map<String, String> challenges = new HashMap<>();

    static {
        challenges.put(KEY, VALUE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String uri = req.getRequestURI();
        if (!uri.startsWith(PREFIX)) {
            resp.sendError(404);
            return;
        }

        final String id = uri.substring(PREFIX.length());
        if (!challenges.containsKey(id)) {
            resp.sendError(404);
            return;
        }

        resp.setContentType("text/plain");
        resp.getOutputStream().print(challenges.get(id));
    }

}
