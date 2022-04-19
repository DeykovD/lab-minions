import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class task02 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "040421");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt =
                connection.prepareStatement("select v.name, m.name, m.age\n" +
                        "from villains as v\n" +
                        "join minions_villains as mv on mv.villain_id = v.id\n" +
                        "join minions as m on mv.minion_id = m.id\n" +
                        "where v.id = ?;");

        int villainId = Integer.parseInt(sc.nextLine());
        stmt.setInt(1, villainId);
        ResultSet villainSet = stmt.executeQuery();

        if(villainSet.next()) {
            for (int i = 0; villainSet.next() ; i++) {
                if(i == 0) {
                    System.out.println("Villain: " + villainSet.getString("v.name"));
                }
                else {
                    System.out.printf("%d. %s %d%n", i, villainSet.getString("m.name"), villainSet.getInt("m.age"));
                }
            }
        }
        else{
            System.out.println("No villain with ID 10 exists in the database.");
        }
        connection.close();
    }
}
