
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class HelloPetstoreServlet extends HttpServlet {

    public void service(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();

        String param = request.getParameter("param");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello Petstore</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1><center>Hello</center></h1>");
        out.println("<h2><center>" + param + "</center></h2>");
        out.println(new Date());
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
