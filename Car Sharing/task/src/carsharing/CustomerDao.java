package carsharing;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    void addCustomer(Customer customer);
    boolean returnCar(Customer customer);
}
