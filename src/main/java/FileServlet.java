import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Scanner;

@WebServlet("/FileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileServlet extends HttpServlet {
    private static final String uploadDir = "uploads";
    private static final DocumentDB documentDB = new DocumentDB();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/file.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String applicationPath = req.getServletContext().getRealPath("");

        String uploadFilePath = applicationPath + File.separator + uploadDir;

        File uploadFolder = new File(uploadFilePath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        PrintWriter writer = resp.getWriter();

        for (Part part : req.getParts()) {
            if (part != null && part.getSize() > 0) {
                String fileName = part.getSubmittedFileName();
                //String contentType = part.getContentType();

                part.write(uploadFilePath + File.separator + fileName);
                Scanner scanner = new Scanner(part.getInputStream());
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }
                System.out.println(builder.toString());
                documentDB.saveDocument(builder);

                writer.append("Uploaded file directory: " + uploadFolder.getAbsolutePath() + File.separator + fileName + "<br>\r\n");
            }
        }

    }
}