package SQL;

import Logger.MyLogger;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**************************************************
 * All SQL-Queries
 **************************************************/
public class Queries {

    public static long failCounter;
    public static Statement query;

    private final Logger LOG = MyLogger.getLogger(Queries.class.getName());

    static {
        try {
            String url = "jdbc:mysql://localhost:3306/grocery_list?serverTimezone=Europe/Vienna";
            String user = "root";
            String pw = "root";
            query = DriverManager.getConnection(url, user, pw).createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return all categories
     */
    public ResultSet getAllCategories() {
        try {
            return query.executeQuery("select * from categories;");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return all categories.Name
     */
    public static List<String> getAllCategories_Name() {
        try {
            ResultSet rs = query.executeQuery("select name from categories order by Name asc;");

            List<String> tmp = new ArrayList<>();
            while (rs.next()) {
                tmp.add(rs.getString("Name"));
            }

            return tmp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return all items
     */
    public ResultSet getAllItems() {
        try {
            return query.executeQuery("select * from items;");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return all items.Name and items.Price
     */
    public static ResultSet getAllItems_NamePrice() {
        try {
            return query.executeQuery("select name, price from items;");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return all items.Name with the same items.Price
     */
    public static ResultSet getAllItems_NameWithSamePrice(Double price) {
        try {
            return query.executeQuery("select name from items i where i.price = '" + price + "';");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return all items.ID
     */
    public static List<Integer> getAllItems_ID() {
        try {
            ResultSet rs = query.executeQuery("select ID from items;");

            List<Integer> tmp = new ArrayList<>();
            while (rs.next()) {
                tmp.add(rs.getInt("ID"));
            }

            return tmp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return all items.Name
     */
    public static List<String> getAllItems_Name() {
        try {
            ResultSet rs = query.executeQuery("select name from items;");

            List<String> tmp = new ArrayList<>();
            while (rs.next()) {
                tmp.add(rs.getString("Name"));
            }

            return tmp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return all items.Price
     */
    public static List<Double> getAllItemsPrice() {
        try {
            ResultSet rs = query.executeQuery("select price from items;");

            List<Double> tmp = new ArrayList<>();
            while (rs.next()) {
                tmp.add(rs.getDouble("price"));
            }

            return tmp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return items.Name with its items.Price
     */
    public static ResultSet getItems_NameAndPrice(String name) {
        try {
            return query.executeQuery("select name, price from items i where i.name = '" + name + "';");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Insert a new item with its price into the items-table.
     */
    public static void insertItem(String category, String item, Double price) {
        try {
            query.executeUpdate(
                    "insert into items (category_name, name, price)" +
                    "values ('" + category + "', '" + item + "'," + "'" + price + "')");
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {

                failCounter++;
            } else { e.printStackTrace(); }
        }
    }

    /**
     * Change the name of an item.
     */
    public static void updateNameOfItem(String oldName, String newName) {
        try {
            query.executeUpdate("update items set name = '" + newName + "' where name = '" + oldName + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chang the price of an item.
     */
    public static void updatePriceOfItem(String item, Double price) {
        try {
            query.executeUpdate("update items set price = '" + price + "' where name = '" + item + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove the item from the items-database.
     */
    public static void deleteItem(String item) {
        try {
            query.executeUpdate("delete from items where name = '" + item + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
