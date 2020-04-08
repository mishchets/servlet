import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (UserDB.select() == null) {
            throw new IllegalStateException("No connection with database");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != "" && password != "" && login != null && password != null) {
            ArrayList<User> users = UserDB.select();
            if (UserCheck.isUserCorrect(users, login, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                resp.addCookie(new Cookie("password", password));
                req.getRequestDispatcher("/hello.jsp").forward(req, resp);
            } else {
                throw new IllegalStateException("Wrong login/password");
            }
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}