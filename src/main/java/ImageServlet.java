import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "SendImageServlet", urlPatterns = {"/image"})
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext servletContext = getServletContext();

        try (InputStream inputStream = servletContext.getResourceAsStream("/Images/image.jpg")) {
            OutputStream outputStream = resp.getOutputStream();
            if (inputStream == null) {
                resp.setContentType("text/plain");
                outputStream.write("Failed when sending image".getBytes());
            } else {
                byte[] buffer = new byte[1024];
                int bytesRead;

                resp.setContentType("image/png");
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}
