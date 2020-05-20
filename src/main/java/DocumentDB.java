import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.sql.*;

public class DocumentDB {
    private static final String url = "jdbc:postgresql://localhost:5432/Servlet";
    private static final String username = "postgres";
    private static final String password = "password";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private static void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveDocument(StringBuilder data, String filename, String path) {
        try {
            String str = "";
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String n = filename.replaceAll("\\.", "|");
                if (n.split("\\|")[1].equals("doc")){
                    FileInputStream fis = new FileInputStream(path+ "\\" +filename);
                    HWPFDocument document = new HWPFDocument(fis);
                    WordExtractor extractor = new WordExtractor(document);
                    String[] fileData = extractor.getParagraphText();
                    for (int i = 0; i < fileData.length; i++)
                    {
                        if (fileData[i] != null)
                            str+= fileData[i] +" ";
                    }

                } else if(n.split("\\|")[1].equals("docx")) {
                    FileInputStream fis = new FileInputStream(path+ "\\" +filename);
                    XWPFDocument document = new XWPFDocument(fis);
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                    str = extractor.getText();
                } else {
                    str = data.toString();
                }

                PreparedStatement statement = conn.prepareStatement("insert into documents (data) values (?)");
                statement.setString(1, str);
                statement.executeUpdate();

                statement.close();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
