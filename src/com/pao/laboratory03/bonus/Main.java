package com.pao.laboratory03.bonus;

import com.pao.laboratory03.bonus.model.*;
import com.pao.laboratory03.bonus.service.TaskService;
import com.pao.laboratory03.bonus.exception.*;

public class Main {
    public static void main(String[] args) {
        TaskService service = TaskService.getInstance();

        System.out.println("=== Adăugare task-uri ===");
        service.addTask("Fix login bug", Priority.CRITICAL);
        service.addTask("Add dark mode", Priority.LOW);
        service.addTask("Update docs", Priority.MEDIUM);
        service.addTask("Fix memory leak", Priority.HIGH);
        service.addTask("Refactor DB layer", Priority.HIGH);
        service.getTasksByPriority(Priority.CRITICAL).forEach(System.out::println);

        System.out.println("\n=== Asignare ===");
        service.assignTask("T001", "Ana");
        service.assignTask("T003", "Mihai");
        service.assignTask("T004", "Elena");

        System.out.println("\n=== Schimbări status ===");
        try {
            service.changeStatus("T001", Status.IN_PROGRESS);
            service.changeStatus("T001", Status.DONE);
            System.out.println("T001: TODO → IN_PROGRESS → DONE ✓");
            service.changeStatus("T001", Status.TODO); // Va pica
        } catch (InvalidTransitionException e) {
            System.out.println("Excepție: " + e.getMessage());
        }

        System.out.println("\n=== Scor urgență (baseDays=5) ===");
        System.out.println("Total: " + service.getTotalUrgencyScore(5));

        System.out.println("\n=== Audit Log ===");
        service.printAuditLog();

        System.out.println("\n=== Erori controlate ===");
        try {
            service.assignTask("T999", "Robot");
        } catch (TaskNotFoundException e) {
            System.out.println("Prins corect: Task inexistent.");
        }
    }
}