package carsharing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    SQLService dbService = new SQLService();

    @Override
    public List<Car> getAllCars(Company company) {
        List<Car> carList = new ArrayList<>();

        try {
            //////////////////////
            String querry = "SELECT CAR.ID, CAR.NAME \n" +
                    "FROM CAR\n" +
                    "INNER JOIN COMPANY ON COMPANY.ID = CAR.COMPANY_ID\n" +
                    "WHERE COMPANY.ID = " + company.getId();
            //////////////////
            ResultSet rs = dbService.dbQuerry(querry);
            int counter = 0;
            while(rs.next()) {
                /// Wałek lekki
                counter++;
                ///
                carList.add(new Car(rs.getString(2), counter));
            }
            rs.close();

            // dbService.statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }
    public List<Car> getAvalibleCars(Company company) {
        List<Car> carList = new ArrayList<>();
        try {
            String querry = "SELECT CAR.NAME\n" +
                    "FROM CAR \n" +
                    "INNER JOIN COMPANY ON COMPANY.ID = CAR.COMPANY_ID \n" +
                    "WHERE COMPANY.ID = " +  company.getId() + " " +
                    "EXCEPT\n" +
                    "SELECT CAR.NAME\n" +
                    "FROM CAR\n" +
                    "INNER JOIN CUSTOMER ON CAR.ID = CUSTOMER.RENTED_CAR_ID;";

            ResultSet rs = dbService.dbQuerry(querry);
            int counter = 0;
            while(rs.next()) {
                counter++;
                carList.add(new Car(rs.getString(1), counter));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    @Override
    public void addCar(Car car) {
            dbService.dbUpdate("INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('" + car.getName() + "','"+ car.getCompany_id() +"');");
        }
}





