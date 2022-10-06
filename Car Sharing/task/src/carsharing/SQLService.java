package carsharing;

import java.sql.*;

public class SQLService {
    /// Drivers
    static final String DB_URL = "jdbc:h2:file:../task/src/carsharing/db/carsharing";
    static final String JDBC_DRIVER = "org.h2.Driver";

    /// Querries
    static final String QUERRY_CREATE_COMPANY_TAB = "CREATE TABLE COMPANY (ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT" +
            " , NAME VARCHAR UNIQUE NOT NULL);";
    static final String QUERRY_CREATE_CAR_TAB = "CREATE TABLE CAR (\n" +
            " ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
            "  NAME VARCHAR NOT NULL UNIQUE,\n" +
            "  COMPANY_ID INT NOT NULL,\n" +
            "  CONSTRAINT fk_companyid FOREIGN KEY (COMPANY_ID)\n" +
            "  REFERENCES COMPANY(ID)\n" +
            " )";
    static final String QUERRY_CREATE_CUSTOMER_TAB = "CREATE TABLE CUSTOMER (\n" +
            " ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
            "  NAME VARCHAR NOT NULL UNIQUE,\n" +
            "  RENTED_CAR_ID INT DEFAULT NULL ,\n" +
            "  CONSTRAINT fk_rentedcarid FOREIGN KEY (RENTED_CAR_ID)\n" +
            "  REFERENCES CAR(ID)\n" +
            " )";

    Connection conn = null;
    Statement statement = null;

    public SQLService() {
        connect();
    }

    public void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void createDB() {
        dbUpdate(QUERRY_CREATE_COMPANY_TAB);
        dbUpdate(QUERRY_CREATE_CAR_TAB);
        dbUpdate(QUERRY_CREATE_CUSTOMER_TAB);
    }
    public void dbUpdate(String querry) {

        try {
            statement = conn.createStatement();
            statement.executeUpdate(querry);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet dbQuerry(String querry) {
        ResultSet result = null;


        try {
            statement = conn.createStatement();
            result = statement.executeQuery(querry);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
