package by.wildhunt.letsencrypt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("SpellCheckingInspection")
public class LetsencryptServlet extends HttpServlet {
    private static final Map<String, String> challenges = new HashMap<>();

    static final String PREFIX = "/.well-known/acme-challenge/";
    static final String KEY = "9gPe-ihZ_we4vQefwKoRRHyAS-pIfMJoflSvNwpTNhQ";
    private static final String VALUE = "9gPe-ihZ_we4vQefwKoRRHyAS-pIfMJoflSvNwpTNhQ.j_gc0OsBVK1hT47GVnksPMuNA5plGOCt_fXOLDSwFJY";

    static {
        //noinspection SpellCheckingInspection
        challenges.put(KEY, VALUE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
