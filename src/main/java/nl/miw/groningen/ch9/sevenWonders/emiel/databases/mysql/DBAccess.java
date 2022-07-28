package nl.miw.groningen.ch9.sevenWonders.emiel.databases.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    private Connection connection;
    private String databaseName;
    private String mainUser;
    private String mainUserPassword;
    private static final String SQL_EXCEPTION = "SQL Exception: ";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PREFIX_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String CONNECTION_SETTINGS = "?useSSL=false" +
            "&allowPublicKeyRetrieval=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";

    public DBAccess(String databaseName, String mainUser, String mainUserPassword) {
        super();
        this.databaseName = databaseName;
        this.mainUser = mainUser;
        this.mainUserPassword = mainUserPassword;
    }

    /**
     * Open database connection
     */
    public void openConnection() {
        String connectionURL = PREFIX_CONNECTION_URL + databaseName + CONNECTION_SETTINGS;
        try {
            System.out.print("Laad de driver... ");
            Class.forName(MYSQL_DRIVER); // laad de JDBC-driver.
            System.out.println("Driver geladen");

            connection = DriverManager.getConnection(connectionURL, mainUser, mainUserPassword);
            System.out.println("OK, Connectie open");
        } catch (ClassNotFoundException driverFout) {
            System.out.println("Driver niet gevonden");
        } catch (SQLException sqlFout) {
            System.out.println(SQL_EXCEPTION + sqlFout.getMessage());
        }
    }

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception connectionFout) {
            System.err.println(connectionFout.getMessage());
        }
    }

    public Connection getConnection()  {
        return connection;
    }
}