package com.pao.laboratory03.bonus.service;

import com.pao.laboratory03.bonus.exception.*;
import com.pao.laboratory03.bonus.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private Map<String, Task> tasksById = new HashMap<>();
    private Map<Priority, List<Task>> tasksByPriority = new EnumMap<>(Priority.class);
    private List<String> auditLog = new ArrayList<>();
    private int idCounter = 1;

    private TaskService() {}
    private static class Holder { private static final TaskService INSTANCE = new TaskService(); }
    public static TaskService getInstance() { return Holder.INSTANCE; }

    public Task addTask(String title, Priority priority) {
        String id = String.format("T%03d", idCounter++);
        Task task = new Task(id, title, priority);
        tasksById.put(id, task);
        tasksByPriority.computeIfAbsent(priority, k -> new ArrayList<>()).add(task);
        auditLog.add(String.format("[ADD] %s: '%s' (%s)", id, title, priority));
        return task;
    }

    public void assignTask(String taskId, String assignee) {
        Task t = Optional.ofNullable(tasksById.get(taskId)).orElseThrow(() -> new TaskNotFoundException(taskId));
        t.setAssignee(assignee);
        auditLog.add(String.format("[ASSIGN] %s → %s", taskId, assignee));
    }

    public void changeStatus(String taskId, Status newStatus) {
        Task t = Optional.ofNullable(tasksById.get(taskId)).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (!t.getStatus().canTransitionTo(newStatus)) {
            throw new InvalidTransitionException(t.getStatus(), newStatus);
        }
        auditLog.add(String.format("[STATUS] %s: %s → %s", taskId, t.getStatus(), newStatus));
        t.setStatus(newStatus);
    }

    public List<Task> getTasksByPriority(Priority p) { return tasksByPriority.getOrDefault(p, List.of()); }

    public Map<Status, Long> getStatusSummary() {
        return tasksById.values().stream().collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }

    public List<Task> getUnassignedTasks() {
        return tasksById.values().stream().filter(t -> t.getAssignee() == null).collect(Collectors.toList());
    }

    public double getTotalUrgencyScore(int baseDays) {
        return tasksById.values().stream()
                .filter(t -> t.getStatus() != Status.DONE && t.getStatus() != Status.CANCELLED)
                .mapToDouble(t -> t.getPriority().calculateScore(baseDays))
                .sum();
    }

    public void printAuditLog() { auditLog.forEach(System.out::println); }
}