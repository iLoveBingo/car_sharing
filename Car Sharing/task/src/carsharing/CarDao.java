package carsharing;

import java.util.List;

public interface CarDao {
    List<Car> getAllCars(Company company);
    void addCar(Car car);
    List<Car> getAvalibleCars(Company company);
}
