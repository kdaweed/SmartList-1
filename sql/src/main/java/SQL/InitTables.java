package SQL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class InitTables {
    public static void main(String[] args) {
        try {
            Queries.query.executeUpdate("drop table if exists grocery_list.items cascade;");
            Queries.query.executeUpdate("drop table if exists grocery_list.categories cascade;");
            Queries.query.executeUpdate("drop table if exists grocery_list.measures cascade;");
        } catch (SQLException e) { e.printStackTrace(); }

        initCategoriesTable();
        initItemsTable();
    }

    /**************************************************
     * Insert all items from Item.txt into items-table
     **************************************************/
    private static void initItemsTable() {
        try {
            Queries.query.executeUpdate("CREATE TABLE grocery_list.items (" +
                                        "ID INT NOT NULL AUTO_INCREMENT," +
                                        "Category_Name VARCHAR(45) NOT NULL," +
                                        "Name VARCHAR(45) NOT NULL," +
                                        "PRICE DOUBLE NULL," +
                                        "PRIMARY KEY (Category_Name, Name)," +
                                        "UNIQUE INDEX ID_UNIQUE (ID ASC) VISIBLE, " +
                                        "CONSTRAINT FK_Category_Name " +
                                        "FOREIGN KEY (Category_Name) " +
                                        "REFERENCES grocery_list.categories (Name) " +
                                        "ON DELETE CASCADE " +
                                        "ON UPDATE CASCADE);");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void initCategoriesTable() {
        try (BufferedReader fileCategoriesTXT = new BufferedReader(
                new FileReader("sql/src/main/resources/Categories.txt"))) {
            Queries.query.executeUpdate("CREATE TABLE grocery_list.categories (" +
                                        "Name VARCHAR(225) NOT NULL," +
                                        "PRIMARY KEY (Name));");

            String insertCategory = fileCategoriesTXT.readLine();
            while (insertCategory != null) {
                insertCategory = insertCategory.replaceAll("\\s", "");
                Queries.query
                        .executeUpdate("insert into grocery_list.categories (name) values ('" + insertCategory + "');");
                insertCategory = fileCategoriesTXT.readLine();
            }
        } catch (SQLException | IOException e) { e.printStackTrace(); }
    }
}
