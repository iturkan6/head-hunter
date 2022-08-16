package com.example.headhunter.CLI_App;

import com.example.headhunter.CLI_App.View.WorkerView;
import com.example.headhunter.CLI_App.database.Connection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorkerView view = new WorkerView();
//        view.showWorker();
        view.updateWorkerInfo();
    }
}
