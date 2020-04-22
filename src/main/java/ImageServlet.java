import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    private static class Imagination {
        private static BufferedImage drawGraphics() {
            BufferedImage imageBuffer = new BufferedImage(500, 200, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imageBuffer.createGraphics();
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            g2.setColor(Color.BLUE);
            g2.drawString("HI", 100, 100);
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, new float[]{10.0f}, 0.0f));
            g2.drawOval(30,30,50,50);
            g2.setStroke(new BasicStroke());
            g2.setColor(Color.red);
            g2.drawRect(70,30, 30,30);

            g2.setPaint(new GradientPaint(250,100,Color.BLUE,350, 150,Color.YELLOW));
            g2.fill (new Ellipse2D.Double(250, 100, 100, 50));
            g2.dispose();
            return imageBuffer;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
        try(ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(Imagination.drawGraphics(), "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}