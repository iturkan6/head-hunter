package com.example.headhunter.CLI_App.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    private static java.sql.Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:hh.db");
        } catch (SQLException ex) {
            System.out.println("Can't establish connection" + ex.getMessage());
        }
    }

    protected Statement createStatement() {
        if (con != null) {
            try {
                return con.createStatement();
            } catch (SQLException ex) {
                System.out.println("Can't create statement" + ex.getMessage());
            }
        }
        return null;
    }

    protected PreparedStatement createPreparedStatement(String statement) {
        if (con != null) {
            try {
                return con.prepareStatement(statement);
            } catch (SQLException ex) {
                System.out.println("Can't create statement" + ex.getMessage());
            }
        }
        return null;
    }


    public void createTables() {
        Statement statement = createStatement();
        if (statement != null) {
            try {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS worker(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL ," +
                        "surname TEXT NOT NULL," +
                        "profession TEXT);");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS employer(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL ," +
                        "surname TEXT NOT NULL," +
                        "company_name TEXT NOT NULL," +
                        "worker_profession TEXT);");
            } catch (SQLException ex) {
                System.out.println("Can't create table");
            }
        }
    }
}
