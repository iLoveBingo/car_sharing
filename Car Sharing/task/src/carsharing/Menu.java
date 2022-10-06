package carsharing;

import java.util.List;
import java.util.Scanner;

public class Menu {
    CompanyDaoImpl company = new CompanyDaoImpl();
    CarDaoImpl cars = new CarDaoImpl();
    CustomerDaoImpl customers = new CustomerDaoImpl();

    public Menu(){
        company.dbService.createDB();
        loginMenu();
    }

    /// loginMenus - Start
    public void loginMenu(){
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        if (choose == 0) {
            System.exit(0);

        } else if (choose == 1) {
            managerMenu();

        } else if (choose == 2) {
            showCustomersMenu();

        } else if (choose == 3) {
            createCustomerMenu();

        } else {
            System.out.println("Zła wartość");
            loginMenu();
        }
    }

    /// userMenus - Start
    public void managerMenu(){
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        if (choose == 1) {
            showCompaniesMenu();
        } else if (choose == 2) {
            createCompanyMenu();
        } else if (choose == 0) {
            loginMenu();
        } else {
            System.out.println("Zła wartość");
            managerMenu();
        }
    }

    /// CompaniesMenus
    public void createCompanyMenu() {
        System.out.println("Enter the company name:");
        Scanner scanner = new Scanner(System.in);

        String nameInput = scanner.nextLine();


        Company companyy = new Company(nameInput);
        company.addCompany(companyy);
        System.out.println("The company was created!");
        managerMenu();
    }
    public void showCompaniesMenu() {
        List<Company> list = company.getAllCompanies();
        System.out.println("Choose a company:");

        if (!list.isEmpty()) {

            /*
            for (Company currentCompany : list) {
                System.out.println(currentCompany.toString());
            }
            */
            list.forEach( x -> System.out.println( (x.getId() >= 4 ? x.getId() - 3: x.getId() ) + ". " + x.getName()));

            System.out.println("0. Back");

            Scanner scanner = new Scanner(System.in);
            int companyID = scanner.nextInt();

            if (companyID != 0) {
                companyMenu(list.get(companyID - 1));
            } else {
                managerMenu();
            }
        } else {
            System.out.println("The company list is empty!");
            try {
                managerMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void companyMenu(Company company) {
        System.out.println(company.getName() + " company:");
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");

        Scanner scanner = new Scanner(System.in);

        int choose = scanner.nextInt();
        if (choose == 2) {
            createCarMenu(company);
        } else if (choose == 1) {
            showCompanyCarsMenu(company);
        } else if (choose == 0) {
            managerMenu();
        } else {
            System.out.println("Zły wybór!");
            companyMenu(company);
        }
    }

    /// CarsMenus
    public void showCompanyCarsMenu(Company company) {
        List<Car> list = cars.getAvalibleCars(company);


        System.out.println(company.getName() + " cars:");

        if (!list.isEmpty()) {
            for (Car currentCar : list) {
                System.out.println(currentCar.toString());
            }
            companyMenu(company);
        } else {
            System.out.println("The car list is empty!");
            companyMenu(company);
        }

    }
    public void createCarMenu(Company company) {
        System.out.println("Enter the car name:");
        Scanner scanner = new Scanner(System.in);

        String nameInput = scanner.nextLine();

        Car car = new Car(company.getId(), nameInput);
        cars.addCar(car);

        System.out.println("The car was added!");

        companyMenu(company);

    }

    /// CustomersMenus

    public void showCustomersMenu() {
        List<Customer> list = customers.getAllCustomers();
        System.out.println("Choose a customer:");

        if (!list.isEmpty()) {
            list.forEach( x -> System.out.println(x.getId() + ". " + x.getName()));
            System.out.println("0. Back");

            Scanner scanner = new Scanner(System.in);
            int customerID = scanner.nextInt();

            if (customerID!= 0) {
                customerMenu(list.get(customerID - 1));
            } else {
                loginMenu();
            }

        } else {
            System.out.println("The customer list is empty!");
            loginMenu();
        }
    }
    public void createCustomerMenu() {
        System.out.println("Enter the customer name:");
        Scanner scanner = new Scanner(System.in);
        String nameInput = scanner.nextLine();

        Customer customer = new Customer(nameInput);
        customers.addCustomer(customer);

        System.out.println("The customer was added!");
        loginMenu();

    }
    public void customerMenu(Customer customer) {
        System.out.println();
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");

        Scanner scanner = new Scanner(System.in);

        int choose = scanner.nextInt();

        if (choose == 1) {
            if(!customer.isCar_rented()) rentTheCarMenu_Companies(customer);
            else {
                System.out.println("You've already rented a car!");
                customerMenu(customer);
            }

        } else if (choose == 2) {

            returnCarMenu(customer);

        } else if (choose == 3) {

            myRentedCarMenu(customer);

        } else if (choose == 0) {

            loginMenu();

        } else {
            System.out.println("Zły wybór!");
            loginMenu();
        }
    }

    public void rentTheCarMenu_Companies(Customer customer) {




        List<Company> list = company.getAllCompanies();
        System.out.println("Choose a company:");

        if (!list.isEmpty()) {

            list.forEach( x -> System.out.println( (x.getId() >= 4 ? x.getId() - 3: x.getId() ) + ". " + x.getName()));

            System.out.println("0. Back");

            Scanner scanner = new Scanner(System.in);
            int companyID = scanner.nextInt();

            if (companyID != 0) {
                rentTheCarMenu_Cars(list.get(companyID - 1), customer);
            } else {
                customerMenu(customer);
            }
        } else {
            System.out.println("The company list is empty!");
            customerMenu(customer);
        }
    }
    public void rentTheCarMenu_Cars(Company company, Customer customer) {
        List<Car> list = cars.getAvalibleCars(company);
        int choosenOption;

        


        if (!list.isEmpty()) {

            System.out.println("Choose a car:");

            for (Car currentCar : list) {
                System.out.println(currentCar.toString());
            }
            System.out.println("0. Back");

            Scanner scanner = new Scanner(System.in);
            choosenOption = scanner.nextInt();

            if (choosenOption != 0) {
                customers.rentTheCar(list.get(choosenOption - 1),company, customer);
                System.out.println("You rented '" + list.get(choosenOption - 1).getName() +"'");
                customer.setCar_rented(true);
                customerMenu(customer);
            } else {
                rentTheCarMenu_Companies(customer);

            }

        } else {
            System.out.println("No available cars in the '"+ company.getName() +"' company");
            rentTheCarMenu_Companies(customer);
        }
    }
    public void returnCarMenu(Customer customer) {
        if(customers.returnCar(customer)) {
            customer.setCar_rented(false);
            System.out.println("You've returned a rented car!");
        } else {
            System.out.println("You didn't rent a car!");
        }
        customerMenu(customer);
    }

    public void myRentedCarMenu(Customer customer) {
        List<String> list = customers.myReturnedCar(customer);
        if(list.isEmpty()) {
            System.out.println("You didn't rent a car!");
            customerMenu(customer);
        } else {
            list.stream()
                    .forEach(System.out::println);
            customerMenu(customer);
        }
    }
}












