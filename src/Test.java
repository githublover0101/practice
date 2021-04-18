import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

    public static void main(String[] args) {
        String URL = "jdbc:mysql://mypay1.devdb.alipay.net:3306/share_mobile?useUnicode=true";
        String username = "";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url);
            
        } catch (ClassNotFoundException e) {

        } // ¶¯Ì¬¼ÓÔØmysqlÇý¶¯

    }

}
