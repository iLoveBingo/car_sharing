package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    public SQLService dbService = new SQLService();

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companiesList = new ArrayList<>();
        String querry = "SELECT ID, NAME FROM COMPANY";
        try {
            ResultSet rs  = dbService.dbQuerry(querry);
            while(rs.next()) {
                companiesList.add(new Company(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companiesList;
    }

    @Override
    public void addCompany(Company company) {
        dbService.dbUpdate("INSERT INTO COMPANY (NAME) VALUES ('" + company.getName() + "');");
    }
}




