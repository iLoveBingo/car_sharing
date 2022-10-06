package carsharing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{

    public SQLService dbService = new SQLService();


    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customersList = new ArrayList<>();
        String querry = "SELECT ID, NAME FROM CUSTOMER";

        try {
            ResultSet rs  = dbService.dbQuerry(querry);
            while(rs.next()) {
                customersList.add(new Customer(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }

    @Override
    public void addCustomer(Customer customer) {

        String querryAddCustomer = "INSERT INTO CUSTOMER (NAME) VALUES ('" +
                customer.getName() +
                "');";
        dbService.dbUpdate(querryAddCustomer);
    }

    public void rentTheCar(Car car, Company company, Customer customer) {
        dbService.dbUpdate("UPDATE CUSTOMER\n" +
                " SET RENTED_CAR_ID = " + car.getId() +
                " WHERE NAME = '" + customer.getName() + "';");
    }

    @Override
    public boolean returnCar(Customer customer) {
        ResultSet rs = dbService.dbQuerry("SELECT RENTED_CAR_ID FROM CUSTOMER " +
                "WHERE NAME = '" + customer.getName() + "'");
        try {
            if(rs.next()) {
                if (rs.getObject(1) == null) {
                    return false;
                } else {
                    dbService.dbUpdate("UPDATE CUSTOMER\n" +
                            "        SET RENTED_CAR_ID = NULL" +
                            "        WHERE NAME = '" + customer.getName() +"';");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Wrócić jak stworzymy wypożyczanie do klienta.
    public List<String> myReturnedCar(Customer customer) {
        List<String> list = new ArrayList<>();
        String querry = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE ID = " + customer.getId() + ";";

        try {
            ResultSet rs  = dbService.dbQuerry(querry);

            rs.next();

            String tmpResultValue = rs.getString(1);

            if(rs.wasNull()) {
                return list;
            } else {
                rs = dbService.dbQuerry("SELECT CAR.NAME, COMPANY.NAME\n" +
                        "FROM CUSTOMER\n" +
                        "INNER JOIN COMPANY ON COMPANY.ID = CAR.COMPANY_ID\n" +
                        "INNER JOIN CAR ON CAR.ID = CUSTOMER.RENTED_CAR_ID\n" +
                        "WHERE CUSTOMER.NAME = '" + customer.getName() +"';");

                //v1.01
                rs.next();
                //

                list.add("Your rented car:");
                list.add(rs.getString(1));
                list.add("Company:");
                list.add(rs.getString(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
