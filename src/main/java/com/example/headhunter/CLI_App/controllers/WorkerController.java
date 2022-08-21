package com.example.headhunter.CLI_App.controllers;

import com.example.headhunter.CLI_App.database.WorkerDAO;
import com.example.headhunter.CLI_App.models.Worker;

import java.util.Map;
import java.util.NoSuchElementException;

public class WorkerController {
    private final WorkerDAO dao = new WorkerDAO();

    public Worker getWorker(int id) {
        return dao.get(id).orElseThrow(NoSuchElementException::new);
    }

    public void updateWorker(int id, int move, String value) {
        if (move < 1 || move > 3) throw new NoSuchElementException("No such field");
        String fieldKey = switch (move) {
            case 1 -> "name";
            case 2 -> "surname";
            case 3 -> "profession";
            default -> "";
        };
        dao.update(id, Map.of(fieldKey, value));
    }

    public void deleteWorker(int id) {
        dao.delete(id);
    }
}
