package com.example.headhunter.CLI_App.database;

import com.example.headhunter.CLI_App.models.Employer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class EmployerDAO implements DAO<Employer> {
    Connection con = new Connection();

    @Override
    public Optional<Employer> get(int w_id) {
        ResultSet resultSet;
        try {
            PreparedStatement statement = con.createPreparedStatement("SELECT * FROM employer WHERE id = ?");
            statement.setInt(1, w_id);
            resultSet = statement.executeQuery();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String com_name = resultSet.getString("company_name");
            String w_profession = resultSet.getString("employer_profession");
            return Optional.of(new Employer(id, name, surname,com_name, w_profession));

        } catch (SQLException ex) {
            System.out.println("Can't select employer" + ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(int id, Map<String, Object> field) {
        String key = "";
        Object value = "";
        for (String k : field.keySet()) {
            key = k;
            value = field.get(k);
        }
        try {
            if (key != null) {
                PreparedStatement statement = con.createPreparedStatement(
                        String.format("UPDATE employer SET %s = ? WHERE id = ?;", key));
                statement.setObject(1, value);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Can't update employer" + ex.getMessage());
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = con.createPreparedStatement("DELETE FROM employer WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Can't delete employer" + ex.getMessage());
        }
    }

    @Override
    public void create(Employer employer) {
        try {
            PreparedStatement statement = con.createPreparedStatement("INSERT INTO employer VALUES(?, ?, ?);");
            statement.setString(1, employer.name());
            statement.setString(2, employer.surname());
            statement.setString(3, employer.companyName());
            statement.setString(4, employer.worker_profession());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Can't create employer" + ex.getMessage());
        }
    }
}
