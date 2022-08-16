package com.example.headhunter.CLI_App.View;

import com.example.headhunter.CLI_App.controllers.WorkerController;
import com.example.headhunter.CLI_App.models.Worker;

import java.util.Scanner;

public class WorkerView {
    private final WorkerController controller = new WorkerController();
    Scanner scanner = new Scanner(System.in);

    public void showWorker() {
        System.out.println("Please input a worker id");
        int id = scanner.nextInt();
        Worker worker = controller.getWorker(id);
        System.out.println(worker.toString());
    }

    public void updateWorkerInfo() {
        System.out.println("Input a worker id");
        int id = scanner.nextInt();
        System.out.println("""
                Select what information you want to update:
                1. Name
                2. Surname
                3. Profession""");
        int move = scanner.nextInt();
        System.out.println("Write a new value");
        String value = scanner.next();
        controller.updateWorker(id, move, value);
        System.out.println("Worker was updated");
    }
}
