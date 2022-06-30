import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/student/getInfo")//
public class Server extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<String> bases = new ArrayList<>();
        bases.add("xaxa");
        response.getWriter().print("{\"x\":\"5\"}");
        response.getWriter().close();
        String s = request.getParameter("name");// по фио тему вкр, руководителя вкр, дата защиты вкр
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

    public void destroy() {
    }
}