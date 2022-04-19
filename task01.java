import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class task01 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "040421");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT name, COUNT(DISTINCT minion_id) AS totalCount FROM villains\n" +
                        "JOIN minions_villains AS mv ON villains.id = mv.villain_id\n" +
                        "GROUP BY name\n" +
                        "HAVING totalCount > 15\n" +
                        "ORDER BY totalCount DESC;");

        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("totalCount"));
        }
        connection.close();
    }
}
