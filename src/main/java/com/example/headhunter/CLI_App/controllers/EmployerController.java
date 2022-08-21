package com.example.headhunter.CLI_App.controllers;

import com.example.headhunter.CLI_App.database.EmployerDAO;
import com.example.headhunter.CLI_App.models.Employer;

import java.util.Map;
import java.util.NoSuchElementException;

public class EmployerController {
    private final EmployerDAO dao = new EmployerDAO();

    public Employer getEmployer(int id) {
        return dao.get(id).orElseThrow(NoSuchElementException::new);
    }

    public void updateEmployer(int id, int move, String value) {
        if (move < 1 || move > 3) throw new NoSuchElementException("No such field");
        String fieldKey = switch (move) {
            case 1 -> "name";
            case 2 -> "surname";
            case 3 -> "worker_profession";
            default -> "";
        };
        dao.update(id, Map.of(fieldKey, value));
    }

    public void deleteEmployer(int id) {
        dao.delete(id);
    }
}
