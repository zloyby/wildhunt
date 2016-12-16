package by.wildhunt.letsencrypt;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

public class LetsencryptServletTest extends Mockito {
    @Test
    public void doGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(response.getOutputStream()).thenReturn(servletOutputStream);
        when(request.getRequestURI()).thenReturn(LetsencryptServlet.PREFIX + LetsencryptServlet.KEY);
        new LetsencryptServlet().doGet(request, response);

        verify(request, atLeast(1)).getRequestURI();
        verify(servletOutputStream).print(LetsencryptServlet.VALUE);
    }

}