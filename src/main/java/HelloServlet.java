import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       HttpSession session = req.getSession();
       session.removeAttribute("login");
       session.invalidate();
       Cookie cookie = new Cookie("password", "");
       cookie.setMaxAge(0);
       resp.addCookie(cookie);
       req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
