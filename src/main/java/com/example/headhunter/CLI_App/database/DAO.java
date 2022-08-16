package com.example.headhunter.CLI_App.database;

import java.util.Map;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(int id);


    void update(int id, Map<String, Object> field);

    void delete(int id);

    void create(T t);
}
