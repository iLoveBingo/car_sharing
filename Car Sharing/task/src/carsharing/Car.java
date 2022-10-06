package carsharing;

public class Car {
    private int id;
    private String name;
    private int company_id;

    public Car(String name) {
        this.name = name;
    }
    public Car(int company_id, String name) {
        this.company_id = company_id;
        this.name = name;
    }
    public Car(String name, int ID) {
        this.id = ID;
        this.name = name;
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

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    @Override
    public String toString() {
        return getId() + ". " + getName();
    }
}
