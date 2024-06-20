import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchProducts {
    public static void main(String[] args) {
        // JDBC URL, username, and password of SQL Server
        String url = "jdbc:sqlserver://DESKTOP-AHLVJ29\\SQLEXPRESS;databaseName=YourDatabaseName";
        String user = "roof";
        String password = "pwd";

        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Creating a statement to execute SQL queries
            Statement statement = connection.createStatement();

            // Query to fetch all products from the Product table
            String query = "SELECT id, name, price_per_unit, active_for_sell FROM Product";

            // Executing the query and obtaining the result set
            ResultSet resultSet = statement.executeQuery(query);

            // Printing the column headers
            System.out.println("Product List:");
            System.out.printf("%-5s %-20s %-15s %s\n", "ID", "Name", "Price Per Unit", "Active for Sell");

            // Iterating over the result set and printing each product
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double pricePerUnit = resultSet.getDouble("price_per_unit");
                boolean activeForSell = resultSet.getBoolean("active_for_sell");

                // Printing each product row
                System.out.printf("%-5d %-20s %-15.2f %b\n", id, name, pricePerUnit, activeForSell);
            }

            // Closing resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
