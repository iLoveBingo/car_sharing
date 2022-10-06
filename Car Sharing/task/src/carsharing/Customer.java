package carsharing;

public class Customer {
    private int id;
    private String name;
    private int rented_car_id;
    Boolean car_rented = false;

    public Customer() {

    }
    public Customer(String name) {
        this.name = name;
    }
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Boolean isCar_rented() {
        return car_rented;
    }

    public void setCar_rented(Boolean car_rented) {
        this.car_rented = car_rented;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getRented_car_id() {
        return rented_car_id;
    }
    public void setRented_car_id(int rented_car_id) {
        this.rented_car_id = rented_car_id;
    }
}
