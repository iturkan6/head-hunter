package com.example.headhunter.CLI_App.database;

import com.example.headhunter.CLI_App.models.Worker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class WorkerDAO implements DAO<Worker> {
    Connection con = new Connection();

    @Override
    public Optional<Worker> get(int w_id) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = con.createPreparedStatement("SELECT * FROM worker WHERE id = ?");
            statement.setInt(1, w_id);
            resultSet = statement.executeQuery();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String profession = resultSet.getString("profession");
            return Optional.of(new Worker(id, name, surname, profession));

        } catch (SQLException ex) {
            System.out.println("Can't select worker" + ex.getMessage());
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
                        String.format("UPDATE worker SET %s = ? WHERE id = ?;", key));
                statement.setObject(1, value);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Can't update worker" + ex.getMessage());
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = con.createPreparedStatement("DELETE FROM worker WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Can't delete worker" + ex.getMessage());
        }
    }

    @Override
    public void create(Worker worker) {
        try {
            PreparedStatement statement = con.createPreparedStatement("INSERT INTO worker VALUES(?, ?, ?);");
            statement.setString(1, worker.name());
            statement.setString(2, worker.surname());
            statement.setString(3, worker.profession());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Can't create worker" + ex.getMessage());
        }
    }
}
