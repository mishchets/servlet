import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet("/IndexServlet")
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
        RequestDispatcher dispatcher = null;
        HttpSession session = null;
        ArrayList<User> users = UserDB.select();
        if (login != "" && password != "" && login != null && password != null && (UserCheck.isUserCorrect(users, login, password))) {
                session = req.getSession(true);
                session.setAttribute("login", login);
                resp.addCookie(new Cookie("password", password));
                dispatcher = req.getRequestDispatcher("/hello.jsp");
        } else {
            //req.getRequestDispatcher("/index.jsp").forward(req, resp);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        dispatcher.forward(req, resp);
    }
}